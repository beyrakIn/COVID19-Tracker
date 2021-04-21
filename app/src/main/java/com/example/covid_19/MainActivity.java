package com.example.covid_19;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.covid_19.api.methods.GetStatistic;
import com.example.covid_19.api.models.BaseResponse;
import com.example.covid_19.api.models.CaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Progress dialog;
    private RelativeLayout rLayout;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<CaseResponse> cases;// = new ArrayList<>();
    private CaseAdapter adapter;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        rLayout = findViewById(R.id.layout);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.recycler);
        dialog = new Progress(this);

        setSwipeLayout();
        setActionBar();
        loadData();
    }

    private void loadData() {
        new Thread(() -> {
            new GetStatistic().getStatistics().enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        cases = response.body().getCaseResponse();
                        setRecycler();
                    } else {
                        loadData();
                    }
                    if (refreshLayout.isRefreshing()) {
                        refreshLayout.setRefreshing(false);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    loadData();
                }
            });
        }).start();
    }


    private void setSwipeLayout() {
        refreshLayout.setOnRefreshListener(this::loadData);
    }


    private void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("coronavirus".toUpperCase());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        }
    }


    private void setRecycler() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CaseAdapter(this, cases);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("EXIT")
                .setMessage("Do you want to exit?")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    MainActivity.super.onBackPressed();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        adapter.notifyDataSetChanged();
        return false;
    }
}

