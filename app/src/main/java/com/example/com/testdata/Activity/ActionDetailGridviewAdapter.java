package com.example.com.testdata.Activity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.com.testdata.R;
import com.example.com.testdata.TestData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GXJ on 2016/4/21.
 */
public class ActionDetailGridviewAdapter extends BaseAdapter{
    private Context context;
    private List<TestData.DataBean> list =new ArrayList<>();
    public ActionDetailGridviewAdapter(Context context, List<TestData.DataBean> list) {
        super();
        this.list = list;
        this.context = context;
    }

    public void addAll(List<TestData.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 6;
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

        convertView = LayoutInflater.from(context).inflate(R.layout.gridview_action_detail,null);
        SimpleDraweeView iv_gv_image = (SimpleDraweeView)convertView.findViewById(R.id.iv_gv_image);
        if(list.size()!=0) {
            iv_gv_image.setImageURI(Uri.parse(list.get(position).getPicSmall()));
        }

        return convertView;
    }
}
