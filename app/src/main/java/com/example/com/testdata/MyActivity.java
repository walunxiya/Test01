package com.example.com.testdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MyActivity extends AppCompatActivity {

    private GridView myGridView;
    private int[] myImage = {R.mipmap.wodefabu,R.mipmap.dingdanguanli,R.mipmap.liuranlishi,R.mipmap.wodeqianbao,R.mipmap.gouwuche
            ,R.mipmap.youhuiquan,R.mipmap.huiyuanzhongxin, R.mipmap.jifenshangcheng,R.mipmap.dianpushoucang};
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        init();
    }

    private void init() {
        myGridView = (GridView) findViewById(R.id.my_gridview);
        adapter = new MyAdapter(this,myImage);
        myGridView.setAdapter(adapter);
    }
}
