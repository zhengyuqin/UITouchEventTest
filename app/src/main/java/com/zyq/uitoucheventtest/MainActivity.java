package com.zyq.uitoucheventtest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

	private final String TAG = "MyActivity";
	private MyButton mBtn;
	private MyLinearLayout mLayout;
	private ScrollView mScrollView;
	private ViewPager mViewPager;
	private FragmentStatePagerAdapter mFragmentStatePagerAdapter;
	private FragmentPagerAdapter mFragmentPagerAdapter;
	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBtn = (MyButton) findViewById(R.id.btn);
		mLayout = (MyLinearLayout) findViewById(R.id.ly);
		//mLayout.setFocusable(true);
		//mLayout.setFocusableInTouchMode(true);
//		mLayout.setClickable(false);
//		mLayout.setLongClickable(false);
		mLayout.setClickable(false);//在这里设置是没有效果的,只有在onTouchListener.onTouch才有效果

		mBtn.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						Log.d("MyButton", "onTouch()-->ACTION_DOWN");
						break;
					case MotionEvent.ACTION_MOVE:
						Log.d("MyButton", "onTouch()-->ACTION_MOVE");
						break;
					case MotionEvent.ACTION_UP:
						Log.d("MyButton", "onTouch()-->ACTION_UP");
						break;
				}
				return false;
			}
		});

		mBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("MyButton", "onClick()");
			}
		});
		mLayout.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
//						mLayout.setClickable(false);
//						mLayout.setLongClickable(false);

						Log.d("MyLinearLayout", "onTouch()-->ACTION_DOWN");
						break;
					case MotionEvent.ACTION_MOVE:
//						mLayout.setClickable(false);
//						mLayout.setLongClickable(false);
						Log.d("MyLinearLayout", "onTouch()-->ACTION_MOVE");
						break;
					case MotionEvent.ACTION_UP:
						Log.d("MyLinearLayout", "onTouch()-->ACTION_UP");
						break;
				}
				return false;
			}
		});

		mLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("MyLinearLayout", "onClick()");
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_UP");
				break;
		}
		boolean result = super.dispatchTouchEvent(ev);
		Log.d(TAG, "dispatchTouchEvent()-->result: " + result);
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouchEvent()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouchEvent()-->ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouchEvent()-->ACTION_UP");
				break;
		}
		boolean result = super.onTouchEvent(ev);
		Log.d(TAG, "onTouchEvent()-->result: " + result);
		return result;
	}


	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouch()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouch()-->ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouch()-->ACTION_UP");
				break;
		}
		return false;
	}
}
