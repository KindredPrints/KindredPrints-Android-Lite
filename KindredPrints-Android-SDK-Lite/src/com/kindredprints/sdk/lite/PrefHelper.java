package com.kindredprints.sdk.lite;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefHelper {
	private static final String APP_SHARED_PREFS = "com.kindredprints.sdk_lite";
	public static final String NO_STRING_VALUE = "none";

	private static final String KEY_APP_KEY = "kp_app_key";
	
	private static final String KEY_NEED_SEND_DATA = "kp_app_need_send";
	
	private static final String KEY_NAV_BAR_COLOR = "kp_nav_bar_color";
	private static final String KEY_BASE_BACKGROUND_COLOR = "kp_base_bg_color";
	private static final String KEY_TEXT_COLOR = "kp_text_color";
	private static final String KEY_BORDER_COLOR = "kp_border_color";
	private static final String KEY_BORDER_ENABLED = "kp_border_enabled";
		
	private static final String KEY_USER_EMAIL = "kp_user_email";
	private static final String KEY_USER_NAME = "kp_user_name";

	private SharedPreferences appSharedPrefs_;
	private Editor prefsEditor_;	
		
	public PrefHelper(Context context) {
		this.appSharedPrefs_ = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
		this.prefsEditor_ = appSharedPrefs_.edit();
	}

	public void setNeedSendData(boolean need) {
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, need);
		this.prefsEditor_.commit();
	}
	
	public boolean getNeedSendData() {
		return this.appSharedPrefs_.getBoolean(KEY_NEED_SEND_DATA, false);
	}
	
 	public void setAppKey(String key) {
		this.prefsEditor_.putString(KEY_APP_KEY, key);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
 	
	public String getAppKey() {
		return this.appSharedPrefs_.getString(KEY_APP_KEY, NO_STRING_VALUE);
	}
	
	public void setUserEmail(String email) {
		this.prefsEditor_.putString(KEY_USER_EMAIL, email);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public String getUserEmail() {
		return this.appSharedPrefs_.getString(KEY_USER_EMAIL, NO_STRING_VALUE);
	}
	
	public void setUserName(String name) {
		this.prefsEditor_.putString(KEY_USER_NAME, name);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public String getUserName() {
		return this.appSharedPrefs_.getString(KEY_USER_NAME, NO_STRING_VALUE);
	}
	
	public void setNavBarColor(int color) {
		this.prefsEditor_.putInt(KEY_NAV_BAR_COLOR, color);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public int getNavBarColor() {
		return this.appSharedPrefs_.getInt(KEY_NAV_BAR_COLOR, -1);
	}
	
	public void setBorderColor(int color) {
		this.prefsEditor_.putInt(KEY_BORDER_COLOR, color);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public int getBorderColor() {
		return this.appSharedPrefs_.getInt(KEY_BORDER_COLOR, -1);
	}
	
	public void setTextColor(int color) {
		this.prefsEditor_.putInt(KEY_TEXT_COLOR, color);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public int getTextColor() {
		return this.appSharedPrefs_.getInt(KEY_TEXT_COLOR, -1);
	}
	
	public void setBackgroundColor(int color) {
		this.prefsEditor_.putInt(KEY_BASE_BACKGROUND_COLOR, color);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public int getBackgroundColor() {
		return this.appSharedPrefs_.getInt(KEY_BASE_BACKGROUND_COLOR, -1);
	}
	
	public void setBorderEnabled(boolean enabled) {
		this.prefsEditor_.putBoolean(KEY_BORDER_ENABLED, enabled);
		this.prefsEditor_.putBoolean(KEY_NEED_SEND_DATA, true);
		this.prefsEditor_.commit();
	}
	
	public boolean getBorderEnabled() {
		return this.appSharedPrefs_.getBoolean(KEY_BORDER_ENABLED, true);
	}
}
