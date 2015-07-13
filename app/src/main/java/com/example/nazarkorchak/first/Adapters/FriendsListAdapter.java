package com.example.nazarkorchak.first.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nazarkorchak.first.R;

import java.util.List;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {

        private List<String> records;

        public FriendsListAdapter(List<String> records) {
            this.records = records;
        }

        /**
         * ???????? ????? View ? ViewHolder ???????? ??????, ??????? ???????????? ????? ??????????????????.
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
            return new ViewHolder(v);
        }

        /**
         * ?????????? ???????? View ??????? ?? ???????? ?????? ? ??????? i
         */
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.name.setText("Item " + i);
            viewHolder.icon.setImageResource(R.drawable.img);
        }

        @Override
        public int getItemCount() {
            return records.size();
        }

        /**
         * ?????????? ?????? ViewHolder, ????????? ?????? ?? ???????.
         */
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