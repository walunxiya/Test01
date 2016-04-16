package com.example.com.testdata;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumActivity extends AppCompatActivity implements Callback<TestData> {

    private ScrollListView listView;
    private ForumAdapter adapter;
    private Context context;
    private List<TestData.DataBean> list = new ArrayList<>();
    private LinearLayout ll_pet;
    private boolean isClick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        context = this;
        NetUtils.getServices().getListByType(4, 30).enqueue(ForumActivity.this);

        listView = (ScrollListView) findViewById(R.id.forumlistview);

        ll_pet = (LinearLayout) findViewById(R.id.ll_pet);
//        listView.addFooterView(view);
        adapter = new ForumAdapter(context, list, 3);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        adapter.setAllData(response.body().getData());
        llBuy(response.body().getData());
    }

    public void llBuy(List<TestData.DataBean> pet){
        for(int position = 0;position<pet.size();position++) {

            View view = LayoutInflater.from(this).inflate(R.layout.pet_detail,null);
            SimpleDraweeView iv_scollview = (SimpleDraweeView) view.findViewById(R.id.iv_chongquanpet);
            TextView tv_petname = (TextView) view.findViewById(R.id.tv_petName);
            final TextView follow = (TextView) view.findViewById(R.id.follow);

            iv_scollview.setImageURI(Uri.parse(pet.get(position).getPicSmall()));
            tv_petname.setText(pet.get(position).getName());
            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isClick==false){
                        follow.setText("已关注");
                        follow.setTextColor(Color.GRAY);
                        follow.setBackgroundResource(R.drawable.textviewnok);
                        isClick = true;
                    }else{
                        follow.setText(" + 关注");
                        follow.setTextColor(getResources().getColor(R.color.lightblue));
                        follow.setBackgroundResource(R.drawable.textviewk);
                        isClick = false;
                    }
                }
            });

            ll_pet.addView(view);
        }
    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }
}
