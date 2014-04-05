package com.kindredprints.sdk.lite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KindredOrderReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("KindredTestBed", "Kindred SDK broadcast received by partner");
	
    	KindredOrderFlow orderFlow = new KindredOrderFlow(context);
    	orderFlow.showCart();
	}

}
