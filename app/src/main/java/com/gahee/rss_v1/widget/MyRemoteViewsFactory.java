package com.gahee.rss_v1.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.gahee.rss_v1.roomDatabase.FavEntities;
import com.gahee.rss_v1.roomDatabase.RepositoryRoom;

import java.util.List;

import static com.gahee.rss_v1.helpers.Constants.FILLIN_INTENT_EXTRA;

public class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context context;
    private final RepositoryRoom repositoryRoom;
    private List<FavEntities> favEntitiesList;


    public MyRemoteViewsFactory(Context context, Intent intent){
        this.context = context;
        Intent intent1 = intent;
        repositoryRoom = new RepositoryRoom(this.context);
        favEntitiesList = repositoryRoom.getMyFavorites();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        favEntitiesList = repositoryRoom.getMyFavorites();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return favEntitiesList != null ? favEntitiesList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(favEntitiesList != null){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName()
            , android.R.layout.simple_list_item_1);

            FavEntities favEntities = favEntitiesList.get(position);
            String articleTitle = favEntities.getArticleTitle();
            remoteViews.setTextViewText(android.R.id.text1, articleTitle);

            Intent fillInIntent = new Intent();
            fillInIntent.putExtra(FILLIN_INTENT_EXTRA, position);
            remoteViews.setOnClickFillInIntent(android.R.id.text1, fillInIntent);

            return remoteViews;
        }else{
            return null;
        }
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
