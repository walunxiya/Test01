package com.example.com.testdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by GXJ on 2016/4/15.
 */
public class MyAdapter  extends BaseAdapter{

    private Context context;
    private int[] image ;
    public MyAdapter(Context context,int[] image) {
        super();
        this.image = image;
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return image[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.mygridview_image,null);
        ImageView myimage = (ImageView)convertView.findViewById(R.id.iv_image);
        myimage.setImageResource(image[position]);
        return convertView;
    }
}
