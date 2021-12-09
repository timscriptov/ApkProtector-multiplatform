package mph.trunksku.apps.dexpro.model;

public class iProject {

	private String mPath;

	private Boolean mClassFilter=false;

	private Boolean mIgnored=true;

	private String mIgnoredClass="";

	private Boolean mResGuard=false;

	private Boolean mAntiMod=false;

	private Boolean mSignCheck=false;

	private Boolean mHookDetection=false;

	private String mHookedApps="";

	private Boolean mDeviceLock=false;

	private String mDeviceIds="";

	private Boolean mCrashCatch=false;

	private int mCrashCatchMode=0;

	private Boolean mWelcome=false;

	private String mWelcomeMsg="";

	private int mWelcomeMode=123;

	private Boolean mStrDexGuard = false;

    private boolean mSplash = false;

    private boolean mObfuscate = false;



    public void setPath(String str) {
		mPath = str;
	}

	public void setClassFilter(Boolean str) {
		mClassFilter = str;
	}

	public void setIgnored(Boolean str) {
        mIgnored = str;
	}

	public void setIgnoredClass(String str) {
        mIgnoredClass = str;
	}

	public void setStrDexGuard(Boolean str) {
        mStrDexGuard = str;
	}
	
	public void setResGuard(Boolean str) {
        mResGuard = str;
	}

	public void setAntiMod(Boolean str) {
        mAntiMod = str;
	}

	public void setSignCheck(Boolean str) {
        mSignCheck = str;
	}

	public void setHookDetection(Boolean str) {
        mHookDetection = str;
	}

	public void setHookedApps(String str) {
        mHookedApps = str;
	}

	public void setDeviceLock(Boolean str) {
        mDeviceLock = str;
	}

	public void setDeviceIds(String str) {
        mDeviceIds = str;
	}

	public void setCrashCatch(Boolean str) {
        mCrashCatch = str;
	}

	public void setCrashCatchMode(int str) {
        mCrashCatchMode = str;
	}

	public void setWelcome(Boolean str) {
        mWelcome = str;
	}

	public void setWelcomeMsg(String str) {
        mWelcomeMsg = str;
	}

	public void setWelcomeMode(int str) {
        mWelcomeMode = str;
	}

    public void setSplash(boolean str) {
        mSplash = str;
	}
    
    public void setObfuscate(boolean str) {
        mObfuscate = str;
	}
    
	// Parse Method

	public String getPath() {
		return mPath;
	}

	public Boolean getClassFilter() {
		return mClassFilter;
	}

	public Boolean getIgnored() {
		return mIgnored;
	}

	public String getIgnoredClass() {
		return mIgnoredClass;
	}

	public boolean getStrDexGuard() {
		return mStrDexGuard;
	}
	
	public boolean getResGuard() {
		return mResGuard;
	}

	public Boolean getAntiMod() {
		return mAntiMod;
	}

	public Boolean getSignCheck() {
		return mSignCheck;
	}

	public Boolean getHookDetection() {
		return mHookDetection;
	}

	public String getHookedApps() {
		return mHookedApps;
	}

	public Boolean getDeviceLock() {
		return mDeviceLock;
	}

	public String getDeviceIds() {
		return mDeviceIds;
	}

	public Boolean getCrashCatch() {
		return mCrashCatch;
	}

	public int getCrashCatchMode() {
		return mCrashCatchMode;
	}

	public Boolean getWelcome() {
		return mWelcome;
	}

	public String getWelcomeMsg() {
		return mWelcomeMsg;
	}

	public int getWelcomeMode() {
		return mWelcomeMode;
	}
    
    public Boolean getSplash() {
        return mSplash;
	}
    
    public Boolean getObfuscate() {
        return mObfuscate;
	}
}
