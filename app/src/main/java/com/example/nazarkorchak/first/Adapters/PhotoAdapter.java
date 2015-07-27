package com.example.nazarkorchak.first.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.model.Photo;

import java.util.List;


public class PhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Photo> imageList;

    // Constructor
    public PhotoAdapter(Context c, List<Photo> list) {
        mContext = c;
        imageList = list;
    }

    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.photo_item, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.image);
        Glide.with(mContext).load(imageList.get(position).getPhoto_604()).into(imageView);

        return grid;
    }
}
