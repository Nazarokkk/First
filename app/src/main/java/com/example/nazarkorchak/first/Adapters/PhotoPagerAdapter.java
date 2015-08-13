package com.example.nazarkorchak.first.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.first.R;
import com.example.nazarkorchak.first.events.ShareImageEvent;
import com.example.nazarkorchak.first.events.ShowToolBarEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;


public class PhotoPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> list;
    public boolean isShowToolBar = false;
    ImageView imageView;

    public PhotoPagerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup ssContainer, int mPosition) {
        imageView = new ImageView(context);
        imageView.setPadding(1, 1, 1, 1);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(list.get(mPosition))
                .asBitmap()
                .fitCenter()
                .into(imageView);
        ((ViewPager) ssContainer).addView(imageView, 0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowToolBar = !isShowToolBar;
                EventBus.getDefault().post(new ShareImageEvent(getLocalBitmapUri((ImageView) v),isShowToolBar));
            }
        });

        return imageView;
    }

    public Uri getLocalBitmapUri(ImageView v) {
        Drawable drawable = v.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) v.getDrawable()).getBitmap();
        } else {
            return null;
        }
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "vk_photo" + System.currentTimeMillis() + ".jpeg");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
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
