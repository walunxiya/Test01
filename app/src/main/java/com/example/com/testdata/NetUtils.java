package com.example.com.testdata;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Joshua_Chang on 16/2/19.
 */
public class NetUtils {
    public interface NewsServices
    {
        @GET("/api/teacher")
        Call<TestData> getListByType(@Query("type") int type, @Query("num") int num);
    }

    static
    {
        services=new Retrofit.Builder()
                .baseUrl("http://www.imooc.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsServices.class);
    }
    private static NewsServices services;

    public static NewsServices getServices()
    {
        return services;
    }
}
