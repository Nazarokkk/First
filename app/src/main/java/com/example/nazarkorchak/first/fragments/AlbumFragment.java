package com.example.nazarkorchak.first.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nazarkorchak.first.Adapters.AlbumsAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.LoadAlbumData;
import com.example.nazarkorchak.first.events.PhotoEvent;
import com.example.nazarkorchak.first.events.SendSearchQueryEvent;
import com.example.nazarkorchak.first.inteface.ShowSearchItem;
import com.example.nazarkorchak.first.model.AlbumImage;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class AlbumFragment extends Fragment implements ShowSearchItem {

    public List<AlbumImage> imageList = new ArrayList<AlbumImage>();
    public List<AlbumImage> searchImageList = new ArrayList<AlbumImage>();

    AlbumsAdapter adapter;
    AlbumsAdapter mSearchAdapter;

    InputMethodManager imm;

    GridView gridView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onEventMainThread(LoadAlbumData event) {

        if (event.listAlbum != null) {

            imageList.addAll(event.listAlbum);

            adapter.notifyDataSetChanged();

        }
    }

    public void onEventAsync(SendSearchQueryEvent event) {
        if (event.message != null) {

            searchImageList.clear();

            for (int i = 0; i < imageList.size(); i++) {
                if (imageList.get(i).getTitle().contains(event.message)) {

                    searchImageList.add(imageList.get(i));

                }
            }

            mSearchAdapter = new AlbumsAdapter(getActivity(),searchImageList);
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gridView.setAdapter(mSearchAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment_albums, container, false);

        gridView = (GridView) view.findViewById(R.id.my_grid_view);
        adapter = new AlbumsAdapter(getActivity(), imageList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                EventBus.getDefault().post(new PhotoEvent(imageList.get(position).getOwner_id(), imageList.get(position).getId()));
            }
        });


        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
