package com.gahee.rss_v1.mainFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.recyclerViewAdapters.MyNewsAdapter;
import com.gahee.rss_v1.roomDatabase.NewsEntities;

import java.util.ArrayList;

public class MyNewsFragment extends Fragment {

    private static final String TAG = MyNewsFragment.class.getSimpleName();
    private RecyclerView myNewsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<NewsEntities> newsEntities;

    public MyNewsFragment(){
        //required empty public constructor
    }

    public void setData(ArrayList<NewsEntities> newsEntities){
        this.newsEntities= newsEntities;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_main_news, container, false);

        //set up recycler view & layout manager
        myNewsRecyclerView = view.findViewById(R.id.main_news_recycler_view);
        layoutManager = new LinearLayoutManager(this.getActivity());
        myNewsRecyclerView.setLayoutManager(layoutManager);

        //fix the constructor of the adapter later
        adapter = new MyNewsAdapter(getContext(), newsEntities);
        myNewsRecyclerView.setAdapter(adapter);
        Log.d(TAG, "onCreateView()");
        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

}
