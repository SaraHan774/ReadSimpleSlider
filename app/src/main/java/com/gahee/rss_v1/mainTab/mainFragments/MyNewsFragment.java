package com.gahee.rss_v1.mainTab.mainFragments;

import android.os.Bundle;
import android.util.Log;
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

import com.gahee.rss_v1.news.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.recyclerViewAdapters.MyNewsAdapter;
import com.gahee.rss_v1.remoteDataSource.ViewModelRemote;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyNewsFragment extends Fragment {

    private static final String TAG = MyNewsFragment.class.getSimpleName();
    private RecyclerView myNewsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<ArrayList<Article>> arrayLists = new ArrayList<>();
    private ArrayList<String> articleTopics = new ArrayList<>();

    public MyNewsFragment(){
        //required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on create () ");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_main_news, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setUpRecyclerView(view);

        ViewModelRemote viewModelRemote = ViewModelProviders.of(this).get(ViewModelRemote.class);
        viewModelRemote.getMutableLiveData().observe(Objects.requireNonNull(getActivity()), new Observer<ArrayList<ArrayList<Article>>>() {
            @Override
            public void onChanged(ArrayList<ArrayList<Article>> arrayLists) {
                //fix the constructor of the adapter later
                adapter = new MyNewsAdapter(getContext(), arrayLists);
                adapter.notifyDataSetChanged(); //???
                myNewsRecyclerView.setAdapter(adapter);
            }
        });

    }

    private void setUpRecyclerView(View view){
        myNewsRecyclerView = view.findViewById(R.id.main_news_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        myNewsRecyclerView.setLayoutManager(layoutManager);
        myNewsRecyclerView.setHasFixedSize(true);
    }



}
