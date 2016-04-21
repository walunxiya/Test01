package com.example.com.testdata;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRingActivity extends AppCompatActivity implements Callback<TestData>{

    private LinearLayout ll_petring;
    private Context context;
    private int[] image = {R.mipmap.chongwuleixing,R.mipmap.chengshileixing,R.mipmap.xingquleixing};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_ring);
        context = this;
        init();

    }
    private void init() {
        ll_petring = (LinearLayout) findViewById(R.id.sv_petring);
        NetUtils.getServices().getListByType(4,30).enqueue(this);
    }


    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        petring(response.body().getData());


    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }

    public void petring(List<TestData.DataBean> list){
        for(int i=0;i<3;i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.petring, null);
            ImageView iv_image = (ImageView) view.findViewById(R.id.iv_petring_scollview);
            TextView more = (TextView) view.findViewById(R.id.tv_petring_more);
            LinearLayout ll_ring = (LinearLayout) view.findViewById(R.id.ll_petring_ring);
            iv_image.setBackgroundResource(image[i]);

            petringdetail(list,ll_ring,more,i);

            ll_petring.addView(view);
        }

    }

    private boolean isClick = false;

    public void petringdetail(List<TestData.DataBean> list,LinearLayout ll_ring,TextView more,final int h){
        for(int i=0;i<5;i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.ring_detail, null);
            SimpleDraweeView iv_image = (SimpleDraweeView) view.findViewById(R.id.iv_petring_image);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_petring_name);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_petring_content);
            tv_name.setText(list.get(i).getName());
            iv_image.setImageURI(Uri.parse(list.get(i).getPicSmall()));
            tv_content.setText(list.get(i).getDescription());
            final ImageView iv_look = (ImageView) view.findViewById(R.id.iv_petring_look);
            tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(PetRingActivity.this,RingActivity.class);
                    startActivity(i);
                }
            });
            iv_look.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isClick){
                        iv_look.setBackgroundResource(R.mipmap.guanzhu);
                        isClick = false;
                    }else{
                        iv_look.setBackgroundResource(R.mipmap.yiguanzhu);
                        isClick = true;
                    }
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context,"第"+h+"张图",Toast.LENGTH_SHORT);

                }
            });

            ll_ring.addView(view);

        }
    }
}
