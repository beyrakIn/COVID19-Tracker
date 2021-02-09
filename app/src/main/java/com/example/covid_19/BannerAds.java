package com.example.covid_19;

import android.app.Activity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

public class BannerAds {
    Activity activity;
    private final AdView mAdView;
    AdRequest adRequest;

    public BannerAds(Activity activity) {
        this.activity = activity;

        MobileAds.initialize(activity, initializationStatus -> {
        });

        mAdView = activity.findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
    }

    public void setBannerAds() {
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdClosed() {
            }
        });
    }

    public void show() {
        mAdView.loadAd(adRequest);
    }

}
