package com.gahee.rss_v1.mainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.R;
import com.gahee.rss_v1.recyclerViewAdapters.MyFavoritesAdapter;

public class MyFavoritesFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    private RecyclerView myFavoritesRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    public MyFavoritesFragment(){
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_my_favorites, container, false);
        myFavoritesRecyclerView = view.findViewById(R.id.my_favorites_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        myFavoritesRecyclerView.setLayoutManager(layoutManager);

        //fix the adapter to pass data from the database
        adapter = new MyFavoritesAdapter(getContext());
        myFavoritesRecyclerView.setAdapter(adapter);

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
