package com.example.nazarkorchak.first.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazarkorchak.first.Adapters.FriendsListAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.FriendsEvent;
import com.example.nazarkorchak.first.events.LoadFriendsData;
import com.example.nazarkorchak.first.events.SendSearchQueryEvent;
import com.example.nazarkorchak.first.inteface.ShowSearchItem;
import com.example.nazarkorchak.first.model.Friend;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class FriendsListFragment extends Fragment implements ShowSearchItem {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private FriendsListAdapter mSearchAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Friend> friendList = new ArrayList<Friend>();
    private List<Friend> searchFriendList = new ArrayList<Friend>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new FriendsEvent());
    }

    public void onEventMainThread(LoadFriendsData event) {
        if (event.friendList != null) {
            friendList.addAll(event.friendList);
            // TODO change data in adapter
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onEventAsync(SendSearchQueryEvent event) {
        if (event.message != null) {

            searchFriendList.clear();

            for (int i = 0; i < friendList.size(); i++) {
                if (friendList.get(i).getFirst_name().contains(event.message) || friendList.get(i).getLast_name().contains(event.message)) {

                    searchFriendList.add(friendList.get(i));
                }
            }

            mSearchAdapter = new FriendsListAdapter(searchFriendList, getActivity());
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setAdapter(mSearchAdapter);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_friend_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FriendsListAdapter(friendList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        friendList.clear();


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().show();
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        searchFriendList.clear();
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
