package com.example.com.testdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity implements Callback<TestData>{

    private LinearLayout ll_service;
    private ScrollListView pet;
    private List<TestData.DataBean> petList = new ArrayList<>();
    private PetAdapter adapter;
    private ScrollView scoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        NetUtils.getServices().getListByType(4,30).enqueue(this);
        init();
    }

    private void init() {
        ll_service = (LinearLayout)findViewById(R.id.ll_service);
        pet = (ScrollListView) findViewById(R.id.petlistview);
        scoll = (ScrollView) findViewById(R.id.scroll);
        adapter = new PetAdapter(this,petList);
        pet.setAdapter(adapter);
        addView();

    }

    public void addView(){
        for(int i=0;i<10;i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.service_detail, null);
            ll_service.addView(view);
        }
    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {

        adapter.setAllData(response.body().getData());
    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }
}
