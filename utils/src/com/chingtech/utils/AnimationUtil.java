package com.chingtech.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/**
 * 动画辅助类
 * 
 * @author 师春雷
 * @date 2015-9-29
 * @version 1.0
 *
 */
public class AnimationUtil {

	private AnimationUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 从控件所在位置移动到控件的底部
	 * 
	 * @return
	 */
	public static TranslateAnimation moveToViewBottom() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件的底部移动到控件所在位置
	 * 
	 * @return
	 */
	public static TranslateAnimation moveToViewLocation() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件的顶部移动到控件所在位置
	 * 
	 * @return
	 */
	public static TranslateAnimation topToViewLocation() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件所在位置移动到控件的顶部
	 * 
	 * @return
	 */
	public static TranslateAnimation moveToViewTop() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件的顶部移动到控件所在位置
	 * 
	 * @return
	 */
	public static TranslateAnimation rightToViewLocation() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件所在位置移动到控件的顶部
	 * 
	 * @return
	 */
	public static TranslateAnimation moveToViewRight() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * view抖动动画
	 * 
	 * @param view
	 * 
	 */
	public static void setShakeAnimation(View view) {
		Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
		translateAnimation.setInterpolator(new CycleInterpolator(3));
		translateAnimation.setDuration(500);
		view.startAnimation(translateAnimation);
	}

	/**
	 * 
	 * 
	 * @param context
	 * @param imageview
	 */
	public static void startAnimation(Context context, final ImageView imageview) {
		Animation mAnimation = AnimationUtils.loadAnimation(context, R.anim.cart_anim);
		mAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				imageview.setVisibility(View.INVISIBLE);
			}
		});

		imageview.setVisibility(View.VISIBLE);
		imageview.startAnimation(mAnimation);
	}
}
