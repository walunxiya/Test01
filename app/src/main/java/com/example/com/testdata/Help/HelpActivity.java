package com.example.com.testdata.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.com.testdata.NetUtils;
import com.example.com.testdata.R;
import com.example.com.testdata.ScrollListView;
import com.example.com.testdata.TestData;
import com.example.com.testdata.adapter.RingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpActivity extends AppCompatActivity implements Callback<TestData> ,AdapterView.OnItemClickListener{

    private ScrollListView lv_help;
    private List<TestData.DataBean> list = new ArrayList<>();
    private RingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        init();
    }

    private void init() {
        NetUtils.getServices().getListByType(4,30).enqueue(this);
        lv_help  = (ScrollListView)findViewById(R.id.lv_help);
        adapter = new RingAdapter(this,list);
        lv_help.setAdapter(adapter);
        lv_help.setOnItemClickListener(this);

    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        adapter.addAll(response.body().getData());

    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(HelpActivity.this, "aaaaaaa", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(HelpActivity.this,HelpDetailActivity.class);
        startActivity(i);
    }
}
