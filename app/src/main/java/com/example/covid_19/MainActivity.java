package com.example.covid_19;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    CustomProgressDialog dialog;
    SearchView searchView;
    RelativeLayout rLayout;
    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    String link, result = "";
    List<CaseObject> caseList;
    CaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        rLayout = findViewById(R.id.layout);
        refreshLayout = findViewById(R.id.swipe_refresh_layout);
        recyclerView = findViewById(R.id.recycler);
        dialog = new CustomProgressDialog(MainActivity.this);
        caseList = new ArrayList<>();

        //Ads
        BannerAds bannerAd = new BannerAds(MainActivity.this);
        bannerAd.setBannerAds();
        bannerAd.show();


        try {
            runWeb();
        } catch (Exception e) {
            Snackbar.make(rLayout, "ERROR", Snackbar.LENGTH_LONG).show();
        }

        setSwipeLayout();
        setActionBar();
    }


    private void setSwipeLayout() {
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(false);
            InterstitialAds interstitialAds = new InterstitialAds(MainActivity.this);
            interstitialAds.setInterstitialAds();
            interstitialAds.show();

            try {
                runWeb();
            } catch (Exception e) {
                Snackbar.make(rLayout, "ERROR", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    private void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("coronavirus".toUpperCase());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        }
    }

    private void runWeb() {
        dialog.start();
        link = "https://covid-193.p.rapidapi.com/statistics";
        caseList.clear();
        result = getUrlAsString(link);
        setRecycler();
        parseToJson(result);
        dialog.stop();
    }

    private void setRecycler() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new CaseAdapter(getApplicationContext(), caseList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void parseToJson(String result) {
        List<JSONObject> lists = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            for (int i = 0; i < jsonArray.length(); i++) {
                lists.add(jsonArray.getJSONObject(i));
            }

            for (JSONObject object : lists) {
                caseList.add(new CaseObject(object));
            }
            Snackbar.make(rLayout, "SUCCESS", Snackbar.LENGTH_LONG).show();
        } catch (JSONException e) {
            Snackbar.make(rLayout, "ERROR", Snackbar.LENGTH_LONG).show();
        }

    }

    public static String getUrlAsString(String url) {
        String a = "";
        try {
            URL urlObj = new URL(url);
            URLConnection con = urlObj.openConnection();

            //con.setDoOutput(false);
            con.addRequestProperty("x-rapidapi-key", "a6bbdf2257mshbddc0d75b517587p164465jsn6bf0b909752f");
            con.setRequestProperty("x-rapidapi-host", "covid-193.p.rapidapi.com");
            con.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            a = response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
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
        MenuItem search_item = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) search_item.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_item) {
        }
        return super.onOptionsItemSelected(item);
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

