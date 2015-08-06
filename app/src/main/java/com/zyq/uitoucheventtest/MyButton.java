package com.zyq.uitoucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.Button;

/**
 * @author zyq 15-8-5
 */
public class MyButton extends Button {

	private final String TAG = "MyButton";

	public MyButton(Context context) {
		super(context);
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
		//这时候是这个view的事件都处理完了,所以这时候这个事件不算该view,
		Log.d(TAG, "dispatchTouchEvent()-->result: " + result);
		return result;
	}

	/**
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final ViewParent parent = getParent();
		if (parent != null) {
			parent.requestDisallowInterceptTouchEvent(true);
		}
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.d(TAG, "onTouchEvent()-->ACTION_DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.d(TAG, "onTouchEvent()-->ACTION_MOVE");
				return false;
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "onTouchEvent()-->ACTION_UP");
				break;
			case MotionEvent.ACTION_CANCEL:
				Log.d(TAG, "onTouchEvent()-->ACTION_CANCEL");
				break;
		}
		return super.onTouchEvent(event);
	}
}
