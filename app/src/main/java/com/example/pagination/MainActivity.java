package com.example.pagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    ProgressBar progressbar;
    RelativeLayout rl_loading;


    int totalServerItems = 21;
    boolean isScrolling = false;
    int listSize = 0;
    int currentItems = 0;
    int totalItems = 0;
    int scrollOutItems = 0;

    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        progressbar = findViewById(R.id.progressbar);
        rl_loading = findViewById(R.id.rl_loading);

        recyclerView = findViewById(R.id.rv);


        list = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);


        loadData();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false;

                    if (listSize < totalServerItems) {
                        loadData();
                    }

                }

            }
        });

    }

    private void loadData() {


        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {

            list2.add(i);
        }


        progressbar.setVisibility(View.VISIBLE);
        rl_loading.setVisibility(View.VISIBLE);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {

            listSize = list.size() + list2.size();
            list.addAll(list2);
            adapter.notifyDataSetChanged();

            progressbar.setVisibility(View.GONE);
            rl_loading.setVisibility(View.GONE);


        }, 1000);

    }
}