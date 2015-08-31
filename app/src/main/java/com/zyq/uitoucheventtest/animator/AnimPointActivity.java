package com.zyq.uitoucheventtest.animator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.zyq.uitoucheventtest.R;

/**
 * @author zyq 15-8-29
 */
public class AnimPointActivity extends ActionBarActivity {

	MyAnimView mAnimView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_animator_point);
	}
}
