package com.example.com.testdata.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.com.testdata.R;
import com.example.com.testdata.TestData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by GXJ on 2016/4/19.
 */
public class GridViewAdapter extends BaseAdapter{

    private List<TestData.DataBean> list;
    private Context context;
    public GridViewAdapter(Context context, List<TestData.DataBean> list) {
        super();
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.gv_ring_detail,null);
        SimpleDraweeView sv = (SimpleDraweeView)convertView.findViewById(R.id.iv_ring);
        sv.setImageURI(Uri.parse(list.get(position).getPicBig()));
        return convertView;
    }
}
