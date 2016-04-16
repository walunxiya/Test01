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
 * Created by GXJ on 2016/4/1.
 */
public class PriceAdapter extends BaseAdapter{
private Context context;
    private List<TestData.DataBean> pet = new ArrayList<>();
    private int size ;
    private SimpleDraweeView iv_scollview;
    private TextView tv_petname;
    private TextView tv_petprice;
    private TextView tv_petlook;
    public PriceAdapter(Context context, List<TestData.DataBean> pet,int size) {
        super();
        this.context = context;
        this.pet = pet;
        this.size = size;
    }

    public void setAllData(Collection<? extends TestData.DataBean> data){
        this.pet.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return size;
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
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.scrollview_detail,null);
        iv_scollview = (SimpleDraweeView) view.findViewById(R.id.iv_scollview);
        tv_petname = (TextView) view.findViewById(R.id.tv_petname);
        tv_petprice = (TextView) view.findViewById(R.id.tv_petprice);
        tv_petlook = (TextView) view.findViewById(R.id.tv_petlook);

        if(pet.size()!=0) {
            iv_scollview.setImageURI(Uri.parse(pet.get(position).getPicSmall()));
            tv_petname.setText(pet.get(position).getName());
            tv_petprice.setText(pet.get(position).getDescription());
            tv_petlook.setText(pet.get(position).getName());
        }
        return view;
    }
}
