package com.example.nazarkorchak.first.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.nazarkorchak.first.Adapters.PhotoAdapter;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.activity.FullScreenPhotoActivity;
import com.example.nazarkorchak.first.events.LoadPhotoData;
import com.example.nazarkorchak.first.events.SendImagesEvent;
import com.example.nazarkorchak.first.model.Photo;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class PhotoFrament extends Fragment {
    public ArrayList<Photo> imageList = new ArrayList<Photo>();

    PhotoAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void onEventMainThread(LoadPhotoData event) {

        if (event.listPhoto != null) {

            imageList.addAll(event.listPhoto);


            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = (View) inflater.inflate(R.layout.fragment_photos, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.my_grid_view);
        adapter = new PhotoAdapter(getActivity(), imageList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), FullScreenPhotoActivity.class);
                //intent.putExtra("photo",imageList.get(position).getPhoto_604());

                EventBus.getDefault().post(new SendImagesEvent(imageList));

                //intent.putExtra("imageList",imageList);
                intent.putExtra("position", position);
                startActivity(intent);
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
