package com.example.covid_19;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

public class InterstitialAds {
    Activity activity;
    InterstitialAd mInterstitialAd;

    public InterstitialAds(Activity activity) {
        this.activity = activity;
    }

    public void setInterstitialAds() {
        MobileAds.initialize(activity, "ca-app-pub-2248916584991987~5950751945");
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId("ca-app-pub-2248916584991987/6058563152");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                //Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                //Toast.makeText(getApplicationContext(), "Thanks for support us!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(activity, "Thanks for support us!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
            }
        });
    }

    public void show(){
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}
