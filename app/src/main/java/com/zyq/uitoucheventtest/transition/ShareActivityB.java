package com.zyq.uitoucheventtest.transition;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.zyq.uitoucheventtest.R;

public class ShareActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_activity_b);
	}

	@SuppressLint("NewApi")
	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		if (Build.VERSION.SDK_INT >= 21) {
			finishAfterTransition();
		} else {
			super.onBackPressed();
		}
	}
	public void onBack(View view){
		onBackPressed();
	}
}
