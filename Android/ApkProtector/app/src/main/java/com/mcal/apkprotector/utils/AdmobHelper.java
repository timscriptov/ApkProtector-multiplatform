package com.mcal.apkprotector.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.mcal.apkprotector.data.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class AdmobHelper {
    public AdRequest adR;
    public AdView adView;
    public RelativeLayout bannerView;
    public InterstitialAd interstitial;
    public RewardedVideoAd mRewardedVideoAd;
    public boolean mShowInterAdsAuto = false;
    public RewardedListener rewardlistener;
    private final AdRequest.Builder adRequest;
    private final Context context;
    private boolean timerads = false;

    AdListener al = new AdListener() {

        @Override
        public void onAdFailedToLoad(int i) {
        }

        @Override
        public void onAdLeftApplication() {
        }

        @Override
        public void onAdOpened() {
        }

        @Override
        public void onAdLoaded() {
            if (mShowInterAdsAuto) {
                interstitial.show();
            }
        }

        @Override
        public void onAdClosed() {
            if (!mShowInterAdsAuto) {
                loadAdsRequest();
            }
        }
    };
    AdListener bal = new AdListener() {

        @Override
        public void onAdClosed() {
        }

        @Override
        public void onAdLeftApplication() {
        }

        @Override
        public void onAdOpened() {
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onAdLoaded() {
            bannerView.setVisibility(0);
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onAdFailedToLoad(int i) {
            bannerView.setVisibility(8);
        }
    };
    RewardedVideoAdListener rvl = new RewardedVideoAdListener() {

        @Override
        public void onRewardedVideoAdClosed() {
        }

        @Override
        public void onRewardedVideoAdLeftApplication() {
        }

        @Override
        public void onRewardedVideoAdOpened() {
        }

        @Override
        public void onRewardedVideoStarted() {
        }

        @Override
        public void onRewardedVideoAdLoaded() {
            rewardlistener.onLoaded();
        }

        @Override
        public void onRewarded(RewardItem rewardItem) {
            rewardlistener.onReward(rewardItem);
        }

        @Override
        public void onRewardedVideoAdFailedToLoad(int i) {
            rewardlistener.onFaild();
        }

        @Override
        public void onRewardedVideoCompleted() {

        }
    };

    public AdmobHelper(Context context2) {
        context = context2;
        adRequest = new AdRequest.Builder();
        adRequest.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
        adView = new AdView(context);
        adView.setAdListener(bal);
        interstitial = new InterstitialAd(context);
        interstitial.setAdListener(al);
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
    }

    public AdmobHelper setMobileAdsId(String str) {
        MobileAds.initialize(context, str);
        return this;
    }

    public AdmobHelper setBannerView(RelativeLayout relativeLayout) {
        bannerView = relativeLayout;
        bannerView.addView(adView);
        return this;
    }

    public AdmobHelper setBannerId(String str) {
        Constants.bannerId = str;
        adView.setAdUnitId(str);
        return this;
    }

    public AdmobHelper setBannerSize(AdSize adSize) {
        adView.setAdSize(adSize);
        return this;
    }

    public AdmobHelper setIntertitialId(String str) {
        Constants.intertialId = str;
        interstitial.setAdUnitId(str);
        return this;
    }

    public AdmobHelper setRewardedId(String str) {
        Constants.rewardedId = str;
        return this;
    }

    public AdmobHelper setRewardAdsListener(RewardedListener rewardedListener) {
        rewardlistener = rewardedListener;
        mRewardedVideoAd.setRewardedVideoAdListener(rvl);
        return this;
    }

    public AdmobHelper setTestDevice(String str) {
        adRequest.addTestDevice(str);
        return this;
    }

    public AdmobHelper setShowInterAdsAuto(boolean z) {
        mShowInterAdsAuto = z;
        return this;
    }

    public AdmobHelper setAdsListener(AdListener adListener) {
        if (adView != null) {
            adView.setAdListener(adListener);
        }
        return this;
    }

    public AdmobHelper buildAdsRequest() {
        adR = adRequest.build();
        return this;
    }

    public AdmobHelper loadBannerAdsRequest() {
        if (!Constants.bannerId.isEmpty()) {
            adView.loadAd(adR);
        }
        return this;
    }

    public AdmobHelper loadAdsRequest() {
        if (!Constants.bannerId.isEmpty()) {
            adView.loadAd(adR);
        }
        if (!Constants.intertialId.isEmpty()) {
            interstitial.loadAd(adR);
        }
        return this;
    }

    public void loadRewardedAds() {
        if (!Constants.rewardedId.isEmpty()) {
            rewardlistener.onLoad();
            mRewardedVideoAd.loadAd(Constants.rewardedId, new AdRequest.Builder().build());
        }
    }

    public AdmobHelper initTimerAds(int time) {
        timerads = true;
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {

            @Override
            public void run() {
                handler.post(() -> {
                    if (adR != null) {
                        if (!Constants.bannerId.isEmpty()) {
                            adView.loadAd(adR);
                        }
                        if (!Constants.intertialId.isEmpty()) {
                            interstitial.loadAd(adR);
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, time);
        return this;
    }

    public void reloadAds() {
        if (!timerads && adR != null) {
            if (!Constants.bannerId.isEmpty()) {
                adView.loadAd(adR);
            }
            if (!Constants.intertialId.isEmpty()) {
                interstitial.loadAd(adR);
            }
        }
    }

    public void showIntertitialAds() {
        if (interstitial != null && interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    public void showRewardedAds() {
        if (!Constants.rewardedId.isEmpty()) {
            this.mRewardedVideoAd.show();
        }
    }

    public interface RewardedListener {
        void onFaild();

        void onLoad();

        void onLoaded();

        void onReward(RewardItem rewardItem);
    }
}