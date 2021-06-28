package com.mcal.apkprotector.utils

object AdsAdmob {
    /*private var interstitialAd: InterstitialAd? = null

    @JvmStatic
    fun loadInterestialAd(context: Context) {
        InterstitialAd.load(context, Constants.intertialId, AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    interstitialAd = p0
                }
            })
    }

    @JvmStatic
    fun showInterestialAd(activity: Activity, callback: (() -> Unit)? = null) {
        if(interstitialAd != null) {
            interstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    interstitialAd = null
                    callback?.invoke()
                }
            }
            interstitialAd!!.show(activity)
        } else {
            callback?.invoke()
        }
    }*/
}