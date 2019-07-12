package com.gahee.rss_v1.mainTab.mainFragments;

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
import com.gahee.rss_v1.mainTab.recyclerViewAdapters.MyNewsAdapter;

import java.util.ArrayList;
import java.util.List;

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

    public void setData(List<ArrayList<Article>> arrayLists){
        this.arrayLists = arrayLists;
        //store article topics in a different list
        Log.d(TAG, "setting data ... Data : " + arrayLists.size());
//        getArticleTopics();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_main_news, container, false);



        Log.d(TAG, "onCreateView()");
        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        //set up recycler view & layout manager
        myNewsRecyclerView = view.findViewById(R.id.main_news_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        myNewsRecyclerView.setLayoutManager(layoutManager);
        myNewsRecyclerView.setHasFixedSize(true);
        //fix the constructor of the adapter later
        adapter = new MyNewsAdapter(getContext(), arrayLists);
        adapter.notifyDataSetChanged(); //???
        myNewsRecyclerView.setAdapter(adapter);
    }

//    private void getArticleTopics(){
//        if(arrayLists != null){
//            for(int i = 0; i < arrayLists.size(); i++){
//                Log.d(TAG, "get article topics...." + arrayLists.get(i).size());
//                String topicTitle = arrayLists.get(i).get(0).getTopicTitle();
//                articleTopics.add(topicTitle);
//                Log.d(TAG, "topic titles : " + topicTitle);
//            }
//        }else{
//            articleTopics.add(null);
//        }
//        Log.d(TAG, "storing article topics in a different list ... article topics : " + articleTopics);
//    }

}
