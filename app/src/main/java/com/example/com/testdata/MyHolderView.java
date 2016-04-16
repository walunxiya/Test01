package com.example.com.testdata;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 常守达  2016/3/29 12:50
 * joshuachang0823@gmail.com
 */
public class MyHolderView implements Holder<String> {
    SimpleDraweeView simpleDraweeView;
    @Override
    public View createView(Context context) {
        simpleDraweeView=new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        return simpleDraweeView;
    }
    @Override
    public void UpdateUI(Context context, int i, String s) {
        simpleDraweeView.setImageURI(Uri.parse(s));
    }
}
