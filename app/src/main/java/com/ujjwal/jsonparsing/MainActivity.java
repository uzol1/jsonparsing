package com.ujjwal.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        getMenuJson();
    }
    public void getMenuJson(){

        RetroFitApiInterface retroFitApiInterface = RetroFitClient.getRetrofit().create(RetroFitApiInterface.class);
        final retrofit2.Call<List<MenuModel>> menumodel=  retroFitApiInterface.getMenu();
        menumodel.enqueue(new Callback<List<MenuModel>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MenuModel>> call, Response<List<MenuModel>> response) {
                RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(MainActivity.this,response.body());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false); recyclerView.setLayoutManager(horizontalLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<List<MenuModel>> call, Throwable throwable) {

            }
        });

    }
}
