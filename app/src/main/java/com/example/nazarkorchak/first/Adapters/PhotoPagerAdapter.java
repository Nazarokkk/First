package com.example.nazarkorchak.first.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


public class PhotoPagerAdapter extends PagerAdapter {

    Context context;
    List<String> list;


    public PhotoPagerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup ssContainer, int mPosition) {
        ImageView imageView = new ImageView(context);
        imageView.setPadding(1, 1, 1, 1);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(list.get(mPosition))
                .fitCenter()
                .into(imageView);
        ((ViewPager) ssContainer).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup Container, int Position,
                            Object Object) {
        ((ViewPager) Container).removeView((ImageView) Object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View View, Object Object) {
        return View == ((ImageView) Object);
    }
}
