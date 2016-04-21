package com.example.com.testdata;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RingActivity extends AppCompatActivity implements Callback<TestData> {

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<String> list_title = new ArrayList<>();
    private ArrayList<RingFragment> fragments = new ArrayList<>();

    private TabAdapter fAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        init();
    }

    public void init() {
        tab = (TabLayout) findViewById(R.id.tl_ring);
        vp = (ViewPager) findViewById(R.id.vp_ring);

        //初始化各fragment
//        ringFragment = new RingFragment();


        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
//        list_title = new ArrayList<>();
////        list_title.add(0, "sfs");


        //设置TabLayout的模式
        tab.setTabMode(TabLayout.MODE_FIXED);
        NetUtils.getServices().getListByType(4, 10).enqueue(this);

        fragments.add(RingFragment.newInstance());
        fragments.add(RingFragment.newInstance());
        fragments.add(RingFragment.newInstance());
        fragments.add(RingFragment.newInstance());


//        MyPagerAdapter mAdapter = new MyPagerAdapter(mViewList);
//        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
//        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

        //为TabLayout添加tab名t_title.newTab().setText(list_title.get(3)));
//        tab.addTab(tab.newTab().setText(list_title.get(0));
//

        //viewpager加载adapter
//        vp.setAdapter(fAdapter);
//        vp.setAdapter(new FragmentStatePagerAdapter() {
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return null;
//            }
//
//
//        });
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager


    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        for(int i=0;i<4;i++) {
            tab.addTab(tab.newTab().setText(response.body().getData().get(i).getName()));
            list_title.add(i,response.body().getData().get(i).getName());
        }

        fAdapter = new TabAdapter(this.getSupportFragmentManager(),fragments,list_title);
        vp.setAdapter(fAdapter);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {
    }


}
