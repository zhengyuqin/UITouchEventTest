package com.zyq.uitoucheventtest.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

import com.zyq.uitoucheventtest.R;

/**
 * @author zyq 15-9-1
 */
public class ActivityA extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		getWindow().setEnterTransition(new Explode());
		getWindow().setExitTransition(new Explode());
		findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(ActivityA.this, ActivityB.class);
				startActivity(it, ActivityOptions.makeSceneTransitionAnimation(ActivityA.this).toBundle());
			}
		});
	}
}
