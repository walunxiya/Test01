package com.example.com.testdata;

/**
 * Demo描述:
 * ListView嵌套GridView使用详解
 * 即ListView的每个Item中都包含一个GridView
 *
 * 注意事项:
 * 由于ListView和GridView都是可滑动的控件.
 * 所以需要自定义GridView,重写其onMeasure()方法.
 * 在该方法中使GridView的高为wrap_content的大小,否则GridView中
 * 的内容只能显示很小一部分
 *
 * 参考资料:
 * 1 http://bbs.csdn.net/topics/380245627
 * 2 http://blog.csdn.net/lsong89/article/details/8598856
 * Thank you very much
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ExView.NewExpandableListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetActivity extends AppCompatActivity implements Callback<TestData>{

    private ExpandableListView ex;
    private NewExpandableListAdapter adapter;
    private List<TestData.DataBean> parent = new ArrayList<>();
    private Map<String,List<TestData.DataBean>> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_pet);
        NetUtils.getServices().getListByType(4, 30).enqueue(this);
        init();
    }

    private void init(){
        ex = (ExpandableListView) findViewById(R.id.ex);
        adapter = new NewExpandableListAdapter(this,parent,map);
        ex.setAdapter(adapter);
    }
    private void initData(){


    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        for(int i=0;i<response.body().getData().size();i++) {
            String key = response.body().getData().get(i).getName();
            map.put(key,response.body().getData());

        }
        parent.addAll(response.body().getData());
        adapter.setAllData(parent,map);

    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }
}
