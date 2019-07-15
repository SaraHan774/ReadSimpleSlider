package com.gahee.rss_v1.mainTab.mainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.recyclerViewAdapters.MyFavoritesAdapter;
import com.gahee.rss_v1.remoteDataSource.ViewModelRemote;
import com.gahee.rss_v1.roomDatabase.FavEntities;
import com.gahee.rss_v1.roomDatabase.RepositoryRoom;
import com.gahee.rss_v1.roomDatabase.ViewModelRoom;

import java.util.List;

public class MyFavoritesFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    private RecyclerView myFavoritesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
//    private RepositoryRoom repositoryRoom;
    private ViewModelRoom viewModelRoom;


    public MyFavoritesFragment(){
        //required public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_my_favorites, container, false);

        myFavoritesRecyclerView = view.findViewById(R.id.my_favorites_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        myFavoritesRecyclerView.setLayoutManager(layoutManager);

        viewModelRoom = ViewModelProviders.of(this).get(ViewModelRoom.class);
        viewModelRoom.getFavoriteNews().observe(this, new Observer<List<FavEntities>>() {
            @Override
            public void onChanged(List<FavEntities> favEntities) {
                adapter = new MyFavoritesAdapter(getContext(), favEntities);
                myFavoritesRecyclerView.setAdapter(adapter);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        }


}
