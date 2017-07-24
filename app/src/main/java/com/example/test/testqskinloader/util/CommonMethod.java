package com.example.test.testqskinloader.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by liye on 2017/7/24.
 */

public class CommonMethod {
	
	private static final String TAG = CommonMethod.class.getSimpleName();
	
	/**
	 * Activity的跳转
	 * 
	 * @param context
	 * @param activityClass
	 */
	public static void startAnotherActivity(Context context, @NonNull Class<? extends Activity> activityClass) {
		Intent intent = new Intent(context, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		
	}
}
