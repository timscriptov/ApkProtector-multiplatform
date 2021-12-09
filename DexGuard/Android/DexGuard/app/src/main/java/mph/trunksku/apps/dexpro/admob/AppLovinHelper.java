package mph.trunksku.apps.dexpro.admob;

import android.app.Activity;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.concurrent.TimeUnit;

public class AppLovinHelper {

	private Activity activity;

	private MaxAdView adView;

	private MaxRewardedAd rewardedAd;

	private MaxInterstitialAd interstitialAd;

	private String interId = "";

	private String rewardId = "";

	private String bannerId = "";

	private AppLovinHelper.RewardedListener rewardListen;

	public interface RewardedListener {
        void onLoad();
        void onLoaded();
        void onReward();
        void onClose();
        void onFail();
    }

    public AppLovinHelper(Activity activity){
		this.activity = activity;
	}

	public AppLovinHelper setBannerId(String id){
		bannerId = id;
		adView = new MaxAdView( id, activity );
		adView.setListener( bListen );
		return this;
	}

	public AppLovinHelper setBannerView(ViewGroup v)
    {
		int width = ViewGroup.LayoutParams.MATCH_PARENT;

		// Get the adaptive banner height.
		int heightDp = MaxAdFormat.BANNER.getAdaptiveSize( activity ).getHeight();
		int heightPx = AppLovinSdkUtils.dpToPx( activity, heightDp );

		adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );
		adView.setExtraParameter( "adaptive_banner", "true" );

		// Set background or background color for banners to be fully functional
		//adView.setBackgroundColor( R.color.background_color );

		v.addView(adView);

        return this;
    }

	public AppLovinHelper setIntertitialId(String id){
		interId = id;
		interstitialAd = new MaxInterstitialAd( id, activity );
        interstitialAd.setListener( iListen );

		return this;
	}

	public AppLovinHelper setRewardedId(String id){
		rewardId = id;
		rewardedAd = MaxRewardedAd.getInstance( id, activity );
        rewardedAd.setListener( rListen );

        rewardedAd.loadAd();
		return this;
	}

	public AppLovinHelper setRewardedListener(RewardedListener listen){
		this.rewardListen = listen;
		return this;
	}

	public void build(){
		AppLovinSdk.getInstance( activity ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( activity, new AppLovinSdk.SdkInitializationListener() {
                @Override
                public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
                {
                    // AppLovin SDK is initialized, start loading ads
					//createInterstitialAd();
					loadAds();
                }
            } );
	}

	public void loadAds(){
		if(!bannerId.isEmpty()){
			adView.loadAd();
		}
		if(!interId.isEmpty()){
			interstitialAd.loadAd();
		}
		if(!rewardId.isEmpty()){
			rewardedAd.loadAd();
		}
	}

	public void loadBanner(){
		if(!bannerId.isEmpty()){
			adView.loadAd();
		}
	}

	public void loadIntertitial(){
		if(!interId.isEmpty()){
			interstitialAd.loadAd();
		}
	}

	public void loadRewarded(){
		if(!rewardId.isEmpty()){
			rewardedAd.loadAd();
		}
	}

	public void showIntertitial(){
		if ( interstitialAd.isReady() )
		{
			interstitialAd.showAd();
		}else{
			loadIntertitial();
		}
	}

	public void showRewarded(){
        if ( rewardedAd.isReady() )
		{
			rewardedAd.showAd();
		}else{
			loadRewarded();
		}
	}

	public void showAds() {
		if ( rewardedAd.isReady() )
		{
			rewardedAd.showAd();
		}else if ( interstitialAd.isReady() )
		{
			interstitialAd.showAd();
		}else {
			loadAds();
		}
	}

	MaxAdViewAdListener bListen = new MaxAdViewAdListener(){

		@Override
		public void onAdLoaded(MaxAd p1) {
		}

		@Override
		public void onAdLoadFailed(String p1, int p2) {
		}

		@Override
		public void onAdDisplayed(MaxAd p1) {
		}

		@Override
		public void onAdHidden(MaxAd p1) {
		}

		@Override
		public void onAdClicked(MaxAd p1) {
		}

		@Override
		public void onAdDisplayFailed(MaxAd p1, int p2) {
		}

		@Override
		public void onAdExpanded(MaxAd p1) {
		}

		@Override
		public void onAdCollapsed(MaxAd p1) {
		}
	};

	MaxAdListener iListen = new MaxAdListener(){

		private int retryAttempt;
// MAX Ad Listener
		@Override
		public void onAdLoaded(final MaxAd maxAd)
		{
			// Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

			// Reset retry attempt
			retryAttempt = 0;
		}

		@Override
		public void onAdLoadFailed(final String adUnitId, final int errorCode)
		{
			// Interstitial ad failed to load 
			// We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)

			retryAttempt++;
			long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

			new Handler().postDelayed( new Runnable()
				{
					@Override
					public void run()
					{
						interstitialAd.loadAd();
					}
				}, delayMillis );
		}

		@Override
		public void onAdDisplayFailed(final MaxAd maxAd, final int errorCode)
		{
			// Interstitial ad failed to display. We recommend loading the next ad
			interstitialAd.loadAd();
		}

		@Override
		public void onAdDisplayed(final MaxAd maxAd) {}

		@Override
		public void onAdClicked(final MaxAd maxAd) {}

		@Override
		public void onAdHidden(final MaxAd maxAd)
		{
			// Interstitial ad is hidden. Pre-load the next ad
			interstitialAd.loadAd();
		}
	};

	MaxRewardedAdListener rListen = new MaxRewardedAdListener(){

		private int retryAttempt;

		@Override
		public void onAdLoaded(final MaxAd maxAd)
		{
			// Rewarded ad is ready to be shown. rewardedAd.isReady() will now return 'true'
            if(rewardListen != null)
				rewardListen.onLoaded();
			// Reset retry attempt
			retryAttempt = 0;
		}

		@Override
		public void onAdLoadFailed(final String adUnitId, final int errorCode)
		{
			// Rewarded ad failed to load 
			// We recommend retrying with exponentially higher delays up to a maximum delay (in this case 64 seconds)

			retryAttempt++;
			long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

			new Handler().postDelayed( new Runnable()
				{
					@Override
					public void run()
					{
						rewardedAd.loadAd();
					}
				}, delayMillis );
		}

		@Override
		public void onAdDisplayFailed(final MaxAd maxAd, final int errorCode)
		{
			// Rewarded ad failed to display. We recommend loading the next ad
			rewardedAd.loadAd();
		}

		@Override
		public void onAdDisplayed(final MaxAd maxAd) {}

		@Override
		public void onAdClicked(final MaxAd maxAd) {}

		@Override
		public void onAdHidden(final MaxAd maxAd)
		{
			// rewarded ad is hidden. Pre-load the next ad
			rewardedAd.loadAd();
		}

		@Override
		public void onRewardedVideoStarted(final MaxAd maxAd) {}

		@Override
		public void onRewardedVideoCompleted(final MaxAd maxAd) {}

		@Override
		public void onUserRewarded(final MaxAd maxAd, final MaxReward maxReward)
		{
			if(rewardListen != null)
				rewardListen.onReward();
			// Rewarded ad was displayed and user should receive the reward
		}
	};
}



