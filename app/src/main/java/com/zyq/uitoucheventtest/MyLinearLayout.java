package com.zyq.uitoucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author zyq 15-8-5
 */
public class MyLinearLayout extends LinearLayout {

	private final String TAG = "MyLinearLayout";

	public MyLinearLayout(Context context) {
		super(context);
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_UP");
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.d(TAG, "dispatchTouchEvent()-->ACTION_CANCEL");
				break;
		}
		boolean result = super.dispatchTouchEvent(event);
		Log.d(TAG, "dispatchTouchEvent()-->result: " + result);
		return result;
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onInterceptTouchEvent()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onInterceptTouchEvent()-->ACTION_MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onInterceptTouchEvent()-->ACTION_UP");
				return true;
			case MotionEvent.ACTION_CANCEL:
				Log.d(TAG, "onInterceptTouchEvent()-->ACTION_CANCEL");
				break;
		}
		boolean result = super.onInterceptTouchEvent(ev);
		Log.d(TAG, "onInterceptTouchEvent()-->result: " + result);
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
//				setFocusable(true);
//				setFocusableInTouchMode(true);
//				setClickable(false);
//				setLongClickable(false);
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.d(TAG, "onTouchEvent()-->ACTION_DOWN");
				break;
			default:
				break;
		}
		return super.onTouchEvent(ev);
	}
}
