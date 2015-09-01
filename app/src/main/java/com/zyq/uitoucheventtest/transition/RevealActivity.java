package com.zyq.uitoucheventtest.transition;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

import com.zyq.uitoucheventtest.R;

/**
 * @author zyq 15-9-1
 */
public class RevealActivity extends ActionBarActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reveal_activity);
		final View view = findViewById(R.id.rect);
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0,
						(float) Math.hypot(view.getWidth(), view.getHeight()));
				animator.setInterpolator(new AccelerateInterpolator());
				animator.setDuration(2000);
				animator.start();
			}
		});
	}
}
