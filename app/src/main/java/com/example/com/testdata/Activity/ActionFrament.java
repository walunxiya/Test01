package com.example.com.testdata.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.com.testdata.NetUtils;
import com.example.com.testdata.R;
import com.example.com.testdata.TestData;
import com.example.com.testdata.adapter.RingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by GXJ on 2016/4/20.
 */
public class ActionFrament extends Fragment implements Callback<TestData>{


    private ListView lv_ring;
    private RingAdapter adapter;
    private List<TestData.DataBean> list = new ArrayList<>();

    public static ActionFrament newInstance() {

        Bundle args = new Bundle();

        ActionFrament fragment = new ActionFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_ring, container, false);
        NetUtils.getServices().getListByType(4,30).enqueue(this);
        lv_ring = (ListView) view.findViewById(R.id.lv_ring);
        adapter = new RingAdapter(getContext(), list);
        lv_ring.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResponse(Call<TestData> call, Response<TestData> response) {
        adapter.addAll(response.body().getData());
    }

    @Override
    public void onFailure(Call<TestData> call, Throwable t) {

    }
}
