package com.chingtech.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

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

	/**
	 * 数字变化动画
	 * 
	 * @param textview
	 * @param start
	 * @param end
	 * @param duration
	 */
	public void setValueAnim(final TextView textview, int start, int end, int duration) {
		ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);// 数字起始-结束
		valueAnimator.setTarget(textview);
		valueAnimator.setDuration(duration);// 动画时间(ms)
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int i = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
				textview.setText(String.valueOf(i));
			}
		});
		valueAnimator.start();
	}

	/**
	 * 淡入淡出
	 * 
	 * @param view
	 */
	public void setViewAlpha(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * 旋转
	 * 
	 * @param view
	 */
	public void setViewRotation(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * 水平方向平移
	 * 
	 * @param view
	 */
	public void setViewTranslationX(View view) {
		float curTranslationX = view.getTranslationX();
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", curTranslationX, -500f,
				curTranslationX);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * 在Y轴方向上对View进行缩放
	 * 
	 * @param view
	 */
	public void setViewScaleY(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 3f, 1f);
		animator.setDuration(5000);
		animator.start();
	}

	/**
	 * 组合动画
	 * 
	 * 实现组合动画功能主要需要借助AnimatorSet这个类，这个类提供了一个play()方法，如果我们向这个方法中传入一个Animator对象
	 * (ValueAnimator或ObjectAnimator
	 * )将会返回一个AnimatorSet.Builder的实例，AnimatorSet.Builder中包括以下四个方法：
	 * 
	 * after(Animator anim) 将现有动画插入到传入的动画之后执行
	 * 
	 * after(long delay) 将现有动画延迟指定毫秒后执行
	 * 
	 * before(Animator anim) 将现有动画插入到传入的动画之前执行
	 * 
	 * with(Animator anim) 将现有动画和传入的动画同时执行
	 */
	public void sss(View view) {
		ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", -500f, 0f);
		ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
		ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(rotate).with(fadeInOut).after(moveIn);
		animSet.setDuration(5000);
		animSet.start();
	}
}
