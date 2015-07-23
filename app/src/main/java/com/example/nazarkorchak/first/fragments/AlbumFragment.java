package com.example.nazarkorchak.first.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.nazarkorchak.first.Adapters.AlbumsAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.LoadAlbumData;
import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class AlbumFragment extends Fragment {

    public List<AlbumImage> imageList = new ArrayList<AlbumImage>();

    AlbumsAdapter adapter;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onEventAsync(LoadAlbumData event) {

        if(event.listAlbum != null) {

            imageList.addAll(event.listAlbum);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment_albums, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.my_grid_view);
        adapter = new AlbumsAdapter(getActivity(),imageList);
        gridView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
