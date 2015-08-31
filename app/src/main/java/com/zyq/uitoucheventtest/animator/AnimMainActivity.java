package com.zyq.uitoucheventtest.animator;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.zyq.uitoucheventtest.R;

import java.util.EnumSet;
import java.util.Iterator;


public class AnimMainActivity extends ActionBarActivity {

	TextView mTvAnimator;
	Button mBtnAnimator;
	AnimatorListener mAnimatorListener = new AnimatorListener() {
		@Override
		public void onAnimationStart(Animator animation) {

		}

		@Override
		public void onAnimationEnd(Animator animation) {

		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTvAnimator = (TextView) findViewById(R.id.text);
		mBtnAnimator = (Button) findViewById(R.id.btn);
		final EnumSet<AnimatorType> currEnumSet = EnumSet.allOf(AnimatorType.class);
		final Iterator<AnimatorType> iterator = currEnumSet.iterator();
		mBtnAnimator.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (iterator.hasNext()) {
					AnimatorType type = iterator.next();
					mBtnAnimator.setText(type.name());
					switch (type) {
						case ValueAnimator:
							animatorWithObject();
							break;
						case ObjectAnimator:
							animatorWithObject();
							break;
						case AnimatorSet:
							animatorWithSet();
							break;
						case AnimatorXml:
							animatorWithXml();
							break;
						case AnimatorWithViewProperty:
							animtorWithViewProperty();
							break;
						case AnimatorPoint:
							Intent intent = new Intent(AnimMainActivity.this, AnimPointActivity.class);
							startActivity(intent);
							break;
					}
				}
			}
		});
	}

	/**
	 * ObjectAnimator的使用:可以有alpha,rotation,translationX,scaleY
	 */
	private void animatorWithObject() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mTvAnimator, "alpha", 1f, 0f, 1f);
		animator.setDuration(5000);
		animator.start();
		animator.addListener(mAnimatorListener);
	}

	/**
	 * 动画组合
	 */
	private void animatorWithSet() {
		ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTvAnimator, "translationX", -800f, 0f);
		ObjectAnimator rotate = ObjectAnimator.ofFloat(mTvAnimator, "rotation", 0f, 360f);
		ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTvAnimator, "alpha", 1f, 0f, 1f);

		AnimatorSet animSet = new AnimatorSet();
		animSet.play(rotate).with(fadeInOut).after(moveIn);
		animSet.setDuration(5000);
		animSet.start();
	}

	private void animatorWithXml() {
		Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.animatorxml);
		animator.setTarget(mTvAnimator);
		animator.start();
	}

	private void animtorWithViewProperty() {
		mTvAnimator.animate().x(getWindow().getDecorView().getWidth()/2).y(getWindow().getDecorView().getHeight()).
				setInterpolator(new AccelerateInterpolator()).setDuration(4000);
	}

	public enum AnimatorType {

		ValueAnimator(1), ObjectAnimator(2), AnimatorSet(3), AnimatorXml(4), AnimatorWithViewProperty(5),
		AnimatorPoint(6);

		private int nType;

		private AnimatorType(int type) {
			this.nType = type;
		}
	}

}
