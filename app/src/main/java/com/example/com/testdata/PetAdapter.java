package com.example.com.testdata;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by GXJ on 2016/4/14.
 */
public class PetAdapter extends BaseAdapter{
    private Context context;
    private List<TestData.DataBean> pet = new ArrayList<>();
//    private int size;

    public PetAdapter(Context context, List<TestData.DataBean> pet) {
        super();
        this.context = context;
        this.pet = pet;
//        this.size = size;
    }

    public void setAllData(Collection<? extends TestData.DataBean> data){
        this.pet.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pet.size();
    }

    @Override
    public Object getItem(int position) {
        return pet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh =null;
        if(vh == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_service, null);
            vh.title = (TextView)convertView.findViewById(R.id.tv_title);
            vh.iv = (SimpleDraweeView) convertView.findViewById(R.id.iv_iamge);
            vh.tv_all = (TextView)convertView.findViewById(R.id.tv_all);
            vh.tv_name = (TextView)convertView.findViewById(R.id.tv_quan);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if(pet.size()!=0) {
            vh.title.setText(pet.get(position).getName());
            vh.iv.setImageURI(Uri.parse(pet.get(position).getPicSmall()));
            vh.tv_name.setText(pet.get(position).getName());
            vh.tv_all.setText(pet.get(position).getDescription());
        }
        return convertView;
    }

    static class ViewHolder{
        SimpleDraweeView iv;
        TextView tv_name;
        TextView tv_all;
        TextView title;
    }
}
