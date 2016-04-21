package com.example.com.testdata.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.com.testdata.MyHolderView;
import com.example.com.testdata.NetUtils;
import com.example.com.testdata.R;
import com.example.com.testdata.TestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ExView.MyGridView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionDetailActivity extends AppCompatActivity implements OnItemClickListener, Callback<TestData> {
    private ConvenientBanner convenientBanner;
    private List<String> mData = new ArrayList<>();
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private MyGridView action_detail_gridview;
    private List<TestData.DataBean> list = new ArrayList<>();
    private ActionDetailGridviewAdapter adapter;
    private TextView num;
    private TextView titleName;
    private Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);

        init();
    }

    private void init() {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        num = (TextView) findViewById(R.id.detail_num);
        titleName = (TextView)findViewById(R.id.tv_titlename);
        titleName.setText("活动详情");
        mData = Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<MyHolderView>() {
            @Override
            public MyHolderView createHolder() {
                return new MyHolderView();
            }
        }, mData)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                .setOnItemClickListener(this);
        convenientBanner.getViewPager().setPageTransformer(true, new DepthPageTransformer());
        action_detail_gridview = (MyGridView)findViewById(R.id.action_detail_gridview);
        NetUtils.getServices().getListByType(4, 30).enqueue(this);
        adapter = new ActionDetailGridviewAdapter(this,list);
        action_detail_gridview.setAdapter(adapter);

        join = (Button)findViewById(R.id.btn_join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActionDetailActivity.this,JoinActivityActivity.class);
                startActivity(i);
                ActionDetailActivity.this.finish();
            }
        });


    }

    /**
     * 广告轮播图
     */
    @Override
    protected void onResume() {
        super.onResume();
        convenientBanner.startTurning(5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        convenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(ActionDetailActivity.this, "点击了第: " + (position + 1) + " 个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        adapter.addAll(response.body().getData());
        num.setText("6/"+response.body().getData().size());
    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }
}
