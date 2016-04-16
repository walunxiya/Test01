package com.example.com.testdata;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, Callback<TestData> {
    private ConvenientBanner convenientBanner;
    private List<String> mData = new ArrayList<>();
    private List<TestData.DataBean> list = new ArrayList<>();
    private LinearLayout ll_price;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private SimpleDraweeView iv_picLeft;
    private SimpleDraweeView iv_picRight;
    private ForumAdapter adapter;
    private Context context;
    private ScrollListView listView;
//    private GridView gridview;
//    private PriceAdapter gridViewAdapter;
    private RelativeLayout rl_head;
    private ScrollChangeScrollView scoll;
    private LinearLayout search;
    private RadioButton forum;
    private LinearLayout buy;
    private RadioButton taopet;
    private RadioButton service;
    private RadioButton mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main2);
        context = this;
        init();
        listener();
    }
    public void init(){
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        iv_picLeft = (SimpleDraweeView) findViewById(R.id.iv_picLeft);
        iv_picRight = (SimpleDraweeView) findViewById(R.id.iv_picRight);
        ll_price = (LinearLayout) findViewById(R.id.ll_price);
        rl_head = (RelativeLayout) findViewById(R.id.rl_head);
        scoll = (ScrollChangeScrollView) findViewById(R.id.scroll);
        search = (LinearLayout) findViewById(R.id.ll_search);
        //-------------------------------淘宠
        taopet = (RadioButton) findViewById(R.id.taopet);
        taopet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PetActivity.class);
                startActivity(i);
            }
        });

        rl_head.getBackground().setAlpha(20);//设置透明度
        //--------------------------------------------------------
        buy = (LinearLayout) findViewById(R.id.ll_buy);
//        gridview = (GridView) findViewById(R.id.gridview);
//        gridViewAdapter = new PriceAdapter(context, list, 3);
//        gridview.setAdapter(gridViewAdapter);

        //---------------------------------论坛
        forum = (RadioButton) findViewById(R.id.forum);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ForumActivity.class);
                startActivity(i);
            }
        });

        mine = (RadioButton) findViewById(R.id.mine);
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MyActivity.class);
                startActivity(i);
            }
        });

        service = (RadioButton) findViewById(R.id.service);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(i);
            }
        });

//        View view = LayoutInflater.from(context).inflate(R.layout.listview_foot, null);
        listView = (ScrollListView) findViewById(R.id.listview);
//        listView.addFooterView(view);
        adapter = new ForumAdapter(context, list, 10);
        listView.setAdapter(adapter);


        NetUtils.getServices().getListByType(4, 30).enqueue(this);
        //----------------------------------------------------
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
        Toast.makeText(MainActivity.this, "点击了第: " + (position + 1) + " 个", Toast.LENGTH_SHORT).show();
    }

    /**
     * 网络请求得到的数据
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        iv_picLeft.setImageURI(Uri.parse(response.body().getData().get(0).getPicSmall()));
        iv_picRight.setImageURI(Uri.parse(response.body().getData().get(1).getPicSmall()));
        adapter.setAllData(response.body().getData());

        llBuy(response.body().getData());
//        gridViewAdapter.setAllData(response.body().getData());
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        View listItem = adapter.getView(10, null, listView);
        listItem.measure(0, 0);
        params.height = listItem.getMeasuredHeight() * 3 + (listView.getDividerHeight()) * 3 + 70; //假设你那“更多”的View高为50
        listView.setLayoutParams(params);

    }

    public void llBuy(List<TestData.DataBean> pet){
        for(int position = 0;position<pet.size();position++) {
        View view = LayoutInflater.from(this).inflate(R.layout.scrollview_detail,null);
        SimpleDraweeView iv_scollview = (SimpleDraweeView) view.findViewById(R.id.iv_scollview);
        TextView tv_petname = (TextView) view.findViewById(R.id.tv_petname);
        TextView tv_petprice = (TextView) view.findViewById(R.id.tv_petprice);
        TextView tv_petlook = (TextView) view.findViewById(R.id.tv_petlook);

                iv_scollview.setImageURI(Uri.parse(pet.get(position).getPicSmall()));
                tv_petname.setText(pet.get(position).getName());
                tv_petprice.setText(pet.get(position).getDescription());
                tv_petlook.setText(pet.get(position).getName());


        buy.addView(view);
        }
    }

    private void listener() {
        scoll.setOnScrollChangedListener(new ScrollChangeScrollView.OnScrollChangedListener() {

            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl,
                                        int oldt) {
                //define it for scroll height
                int lHeight = 400;
                if (t <= lHeight) {
                    int progress = (int) (new Float(t) / new Float(lHeight) * 255);
                    rl_head.getBackground().setAlpha(progress);
                    search.getBackground().setAlpha(255);
                } else {
                    rl_head.getBackground().setAlpha(255);
                    search.getBackground().setAlpha(255);
                }

            }
        });
    }


    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }


}
