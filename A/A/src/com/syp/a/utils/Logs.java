package com.syp.a.utils;

import com.syp.a.BuildConfig;
import android.util.Log;

public class Logs {
	
	private static String TAG = "A7Mobile";
	
	public static void i(String msg){
		if(BuildConfig.DEBUG){
			Log.i(TAG, msg);
		}
	}
	
	public static void e(String msg){
		if(BuildConfig.DEBUG){
			Log.i(TAG, msg);
		}
	}

}
