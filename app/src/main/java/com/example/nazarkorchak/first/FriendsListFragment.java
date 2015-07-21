package com.example.nazarkorchak.first;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nazarkorchak.first.Adapters.FriendsListAdapter;
import com.example.nazarkorchak.first.api.api;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FriendsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Friend> friendList;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new LoadFriendsData());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = (View) inflater.inflate(R.layout.friends_list_layout, container, false);

      //  RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);


       // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
      //  mLayoutManager = new LinearLayoutManager(getActivity());
      //  mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
      //  mAdapter = new FriendsListAdapter(friendList,getActivity());
       // mRecyclerView.setAdapter(mAdapter);


        return view;
    }

}
