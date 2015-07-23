package com.example.nazarkorchak.first.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.events.AlbumEvent;
import com.example.nazarkorchak.first.model.Friend;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.inteface.ItemClickListener;

import java.util.List;

import de.greenrobot.event.EventBus;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

    private List<Friend> friendList;
    private Context context;

    public FriendsListAdapter(List<Friend> friendList, Context context) {
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

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                EventBus.getDefault().post(new AlbumEvent(friendList.get(position).getUser_id()));
               // Toast.makeText(context, "#" + position + " - " + friendList.get(position).getUser_id() + " (Long click)", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != friendList ? friendList.size() : 0);
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private ImageView icon;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.recyclerViewItemName);
            icon = (ImageView) itemView.findViewById(R.id.recyclerViewItemIcon);

            itemView.setOnClickListener(this);

        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }
    }
}