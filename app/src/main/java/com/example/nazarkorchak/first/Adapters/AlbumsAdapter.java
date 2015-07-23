package com.example.nazarkorchak.first.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.List;


public class AlbumsAdapter extends BaseAdapter {
    private Context mContext;
    private List<AlbumImage> imageList;

    // Constructor
    public AlbumsAdapter(Context c, List<AlbumImage> list) {
        mContext = c;
        imageList = list;
    }

    public int getCount() {
        return imageList.size();
    }

    public Object getItem(int position) {
        return imageList.get(position);
    }

    public long getItemId(int position) {
        return imageList.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.gridview_item, parent, false);
        } else {
            grid = (View) convertView;
        }

        ImageView imageView = (ImageView) grid.findViewById(R.id.image);
        TextView textView = (TextView) grid.findViewById(R.id.text);
        Glide.with(mContext).load(imageList.get(position).getThumb_src()).into(imageView);
        textView.setText(imageList.get(position).getTitle());

        return grid;
    }
}