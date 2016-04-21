package com.example.com.testdata.Activity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.com.testdata.R;
import com.example.com.testdata.TestData;
import com.example.com.testdata.adapter.GridViewAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ExView.MyGridView;

/**
 * Created by GXJ on 2016/4/19.
 */
public class ActionAdapter extends BaseAdapter{
    private Context context;
    private List<TestData.DataBean> list;
    private GridViewAdapter adapter;

    public ActionAdapter(Context context, List<TestData.DataBean> list) {
        super();
        this.context = context;
        this.list = list;
    }

    public void addAll(List<TestData.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
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

        ViewHolder vh = null;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.action_lv_detail,null);
            vh.sv_vp_image = (SimpleDraweeView) convertView.findViewById(R.id.sv_vp_image);
            vh.tv_vp_ringname = (TextView) convertView.findViewById(R.id.tv_vp_ringname);
            vh.tv_vp_ringtime = (TextView) convertView.findViewById(R.id.tv_vp_ringtime);
            vh.gv_vp_ringphoto = (MyGridView) convertView.findViewById(R.id.gv_vp_ringphoto);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.sv_vp_image.setImageURI(Uri.parse(list.get(position).getPicSmall()));
        vh.tv_vp_ringname.setText(list.get(position).getName());
        vh.tv_vp_ringtime.setText(list.get(position).getName());
        adapter = new GridViewAdapter(context,list);
        vh.gv_vp_ringphoto.setAdapter(adapter);
        return convertView;
    }

    class ViewHolder{
        SimpleDraweeView sv_vp_image;
        TextView tv_vp_ringname;
        TextView tv_vp_ringtime;
        MyGridView gv_vp_ringphoto;
    }
}
