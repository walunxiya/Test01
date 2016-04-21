package com.example.com.testdata.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.com.testdata.R;

import java.util.ArrayList;

public class ActionActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private ActionTabAdapter fAdapter;

    private ArrayList<String> list_title = new ArrayList<>();
    private ArrayList<ActionFrament> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        init();

    }

    private void init() {

        tab = (TabLayout) findViewById(R.id.tl_action);
        vp = (ViewPager) findViewById(R.id.vp_action);

        list_title.add("全部");
        list_title.add("同城活动");
        tab.addTab(tab.newTab().setText(list_title.get(0)));
        tab.addTab(tab.newTab().setText(list_title.get(1)));
        tab.setTabMode(TabLayout.MODE_FIXED);
        fragments.add(ActionFrament.newInstance());
        fragments.add(ActionFrament.newInstance());

        fAdapter = new ActionTabAdapter(this.getSupportFragmentManager(),fragments,list_title);
        vp.setAdapter(fAdapter);
        tab.setupWithViewPager(vp);


    }
}
