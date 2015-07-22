package com.example.nazarkorchak.first.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.Friend;
import com.example.nazarkorchak.first.R;

import java.util.List;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> implements View.OnClickListener {

    private List<Friend> friendList;
    private Context context;

    public FriendsListAdapter(List<Friend> friendList,Context context) {
        this.friendList = friendList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Friend friend = friendList.get(i);
        viewHolder.name.setText(friend.getFirst_name() + " " + friend.getLast_name());
        Glide.with(context).load(friend.getPhoto_100()).into(viewHolder.icon);

        viewHolder.name.setOnClickListener(this);
        viewHolder.icon.setOnClickListener(this);

    }


    @Override
    public int getItemCount() {
        return (null != friendList ? friendList.size() : 0);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context,"Click",Toast.LENGTH_LONG).show();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
            icon = (ImageView) itemView.findViewById(R.id.recyclerViewItemIcon);
        }


    }
}