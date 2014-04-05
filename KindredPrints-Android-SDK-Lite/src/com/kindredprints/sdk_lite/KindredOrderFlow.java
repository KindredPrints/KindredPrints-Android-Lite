package com.kindredprints.sdk_lite;

import java.util.ArrayList;

import com.kindred.kindredprints_android_sdk.KPhoto;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

public class KindredOrderFlow {
	private Context context_;
	private PrefHelper pHelper_;
	
	private static final String KEY_AUTH_KEY = "kp_app_key";
	private static final String KEY_USER_NAME = "kp_user_name";
	private static final String KEY_USER_EMAIL = "kp_user_email";
	private static final String KEY_BORDER_COLOR = "kp_border_color";
	private static final String KEY_BORDER_ENABLED = "kp_border_enabled";
	private static final String KEY_NAV_COLOR = "kp_nav_color";
	private static final String KEY_BACKGROUND_COLOR = "kp_background_color";
	private static final String KEY_TEXT_COLOR = "kp_text_color";
	private static final String KEY_PICTURES = "kp_pictures";
	
	public static final int KP_RESULT_CANCELLED = 101;
	public static final int KP_RESULT_PURCHASED = 102;
	
	static private ArrayList<KPhoto> photosToSend;
	
	public KindredOrderFlow(Context context) {
		this.context_ = context;
		this.pHelper_ = new PrefHelper(context);
		if (photosToSend == null) {
			photosToSend = new ArrayList<KPhoto>();
		}
	}
	
	public KindredOrderFlow(Context context, String key) {
		this.context_ = context;
		this.pHelper_ = new PrefHelper(context);
		if (photosToSend == null) {
			photosToSend = new ArrayList<KPhoto>();
		}
		this.pHelper_.setAppKey(key);
	}
	
	public void setAppKey(String key) {
		this.pHelper_.setAppKey(key);
	}
	
	// CART MANAGEMENT
	
	public void addImageToCart(KPhoto photo) {
		flexAddToCart(photo);
	}
	
	public void showCartWithImages(ArrayList<KPhoto> photos) {
		for (KPhoto photo : photos) {
			flexAddToCart(photo);
		}
		showCart();
	}
	
	public void showCart() {
		PackageManager manager = this.context_.getPackageManager();
		try {
		    Intent i = manager.getLaunchIntentForPackage("com.kindredprints.kindredphotoprints");
		    if (i == null)
		        throw new PackageManager.NameNotFoundException();
		    i.setType("show_cart");
		    i.addCategory(Intent.CATEGORY_LAUNCHER);
		    if (this.pHelper_.getNeedSendData()) {
		    	i = prepareIntent(i);
			    this.pHelper_.setNeedSendData(false);
		    }
		    this.context_.startActivity(i);
			photosToSend.clear();

		} catch (PackageManager.NameNotFoundException e) {
			try {
			    this.context_.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.kindredprints.kindredphotoprints"))); 
			} catch (android.content.ActivityNotFoundException anfe) {
				 this.context_.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kindredprints.kindredphotoprints")));
			}
		}
	}
	
	// OPTIONAL CONFIG
	
	public void preRegisterEmail(String email) {
		this.pHelper_.setUserEmail(email);
		this.pHelper_.setUserName("a Kindred user");
	}
	public void preRegisterEmail(String email, String name) {
		this.pHelper_.setUserEmail(email);
		this.pHelper_.setUserName(name);
	}
	
	public void setNavBarBackgroundColor(int color) {
		this.pHelper_.setNavBarColor(color);
	}
	public void setBaseBackgroundColor(int color) {
		this.pHelper_.setBackgroundColor(color);
	}
	public void setTextColor(int color) {
		this.pHelper_.setTextColor(color);
	}
	public void setImageBorderColor(int color) {
		this.pHelper_.setBorderColor(color);
	}
	public void setImageBorderDisabled(boolean disabled) {
		this.pHelper_.setBorderEnabled(!disabled);
	}
	
	// ADD PHOTOS
	
	private void flexAddToCart(KPhoto photo) {
		this.pHelper_.setNeedSendData(true);
		photosToSend.add(photo);
	}
	
	// PREP INTENT

	private Intent prepareIntent(Intent i) {
		i.putExtra(KEY_AUTH_KEY, this.pHelper_.getAppKey());
		if (!this.pHelper_.getUserName().equals(PrefHelper.NO_STRING_VALUE)) {
			i.putExtra(KEY_USER_NAME, this.pHelper_.getUserName());
		}
		if (!this.pHelper_.getUserEmail().equals(PrefHelper.NO_STRING_VALUE)) {
			i.putExtra(KEY_USER_EMAIL, this.pHelper_.getUserEmail());
		}
		if (this.pHelper_.getBackgroundColor() != -1) {
			i.putExtra(KEY_BORDER_COLOR, this.pHelper_.getBorderColor());
		}
		i.putExtra(KEY_BORDER_ENABLED, this.pHelper_.getBorderEnabled());
		if (this.pHelper_.getNavBarColor() != -1) { 
			i.putExtra(KEY_NAV_COLOR, this.pHelper_.getNavBarColor());
		}
		if (this.pHelper_.getBackgroundColor() != -1) {
			i.putExtra(KEY_BACKGROUND_COLOR, this.pHelper_.getBackgroundColor());
		}
		if (this.pHelper_.getTextColor() != -1) {
			i.putExtra(KEY_TEXT_COLOR, this.pHelper_.getTextColor());
		}
		Log.i("KindredTestBed", "count of photos to be added to bundle = " + photosToSend.size());

		i.putParcelableArrayListExtra(KEY_PICTURES, photosToSend);		
		return i;
	}
}
