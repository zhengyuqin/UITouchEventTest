package com.zyq.uitoucheventtest.transition;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyq.uitoucheventtest.R;

public class ShareActivityA extends Activity {
	private TextView mViewContent;
	private ImageView mViewImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		setContentView(R.layout.share_activity_a);
		mViewContent = (TextView) findViewById(R.id.tv_content);
		mViewImage = (ImageView) findViewById(R.id.iv);
	}

	@SuppressLint("NewApi")
	public void onActivityAnim(View view) {
		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().setSharedElementEnterTransition(new Explode());

			Intent intent = new Intent(this, ShareActivityB.class);
			// 一个共有元素
//			 ActivityOptions options =
//			 ActivityOptions.makeSceneTransitionAnimation(
//			 this, mViewImage, "image");
			 
			 //多个共有元素
			Pair[] pairs = new Pair[2];
			pairs[0] = Pair.create(mViewContent, "text");
			pairs[1] = Pair.create(mViewImage, "image");
			ActivityOptions options = ActivityOptions
					.makeSceneTransitionAnimation(ShareActivityA.this, pairs);
			startActivity(intent, options.toBundle());
		}else{
			Toast.makeText(this, "sorry~", Toast.LENGTH_SHORT).show();
		}
	}

//	@SuppressLint("NewApi")
//	private void showView(View view) {
//		view.clearAnimation();
//		// get the center for the clipping circle
//		int cx = (view.getLeft() + view.getRight()) / 2;
//		int cy = (view.getTop() + view.getBottom()) / 2;
//
//		// get the final radius for the clipping circle
//		int finalRadius = view.getWidth();
//
//		// create and start the animator for this view
//		// (the start radius is zero)
//		Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
//				0, finalRadius);
//		anim.start();
//	}
//
//	@SuppressLint("NewApi")
//	private void hiddenView(final View myView) {
//		myView.clearAnimation();
//		// get the center for the clipping circle
//		int cx = (myView.getLeft() + myView.getRight()) / 2;
//		int cy = (myView.getTop() + myView.getBottom()) / 2;
//
//		// get the initial radius for the clipping circle
//		int initialRadius = myView.getWidth();
//
//		// create the animation (the final radius is zero)
//		Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy,
//				initialRadius, 0);
//
//		// make the view invisible when the animation is done
//		anim.addListener(new AnimatorListenerAdapter() {
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				super.onAnimationEnd(animation);
//				myView.setVisibility(View.INVISIBLE);
//			}
//		});
//
//		// start the animation
//		anim.start();
//	}
}
