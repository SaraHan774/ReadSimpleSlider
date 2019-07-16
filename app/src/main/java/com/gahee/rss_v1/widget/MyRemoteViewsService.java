package com.gahee.rss_v1.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
