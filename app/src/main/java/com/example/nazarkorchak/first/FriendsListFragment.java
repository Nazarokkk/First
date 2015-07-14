package com.example.nazarkorchak.first;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazarkorchak.first.Adapters.FriendsListAdapter;

import java.util.ArrayList;


public class FriendsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.friends_list_layout, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new FriendsListAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    private ArrayList<String> getDataSet() {

        ArrayList<String> mDataSet = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDataSet.add("item" + i);
        }
        return mDataSet;
    }
}
