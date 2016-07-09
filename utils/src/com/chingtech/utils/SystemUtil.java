package com.chingtech.utils;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;

public class SystemUtil {

	private SystemUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static final String KEY_APP_KEY = "JPUSH_APPKEY";

	static PackageManager manager;

	/***
	 * 得到程序名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		String appName = null;
		try {
			manager = context.getPackageManager();
			appName = context.getApplicationInfo().loadLabel(manager).toString();
			return appName;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到程序图标
	 * 
	 * @param context
	 * @param icon_id
	 * @return
	 */
	public static Parcelable getAppIcon(Context context, int icon_id) {
		try {
			Parcelable icon = Intent.ShortcutIconResource.fromContext(context, icon_id);
			return icon;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getPkName(Context context) {
		try {
			String pkName = context.getPackageName();
			return pkName;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取版本名
	 * 
	 * @param context
	 * @return 当前应用的版本名
	 */
	public static String getVersionName(Context context) {
		try {
			manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
			String versionName = info.versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @param context
	 * @return 当前应用的版本号
	 */
	public static int getVersionCode(Context context) {
		try {
			manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPkName(context), 0);
			int versionCode = info.versionCode;
			return versionCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * 获取ApiKey
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppKey(Context context) {
		Bundle metaData = null;
		String appKey = null;
		try {
			ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
			if (null != ai)
				metaData = ai.metaData;
			if (null != metaData) {
				appKey = metaData.getString(KEY_APP_KEY);
				if ((null == appKey) || appKey.length() != 24) {
					appKey = null;
				}
			}
		} catch (NameNotFoundException e) {

		}
		return appKey;
	}

	public static String getImei(Context context, String imei) {
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId();
		} catch (Exception e) {
			e.getMessage();
		}
		return imei;
	}

	/**
	 * 隐藏虚拟键盘
	 * 
	 * @param activity
	 */
	public static void hideKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 判断隐藏软键盘是否弹出
		if (imm.isActive()) {
			try {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 安装应用
	 * 
	 * @param context
	 * @param fileName
	 */
	public static void update(Context context, String fileName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
}
