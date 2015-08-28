package com.zyq.uitoucheventtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author zyq 15-7-20
 */
public class ChildScrollerViewFragment extends Fragment {

	private static final boolean DEBUG = true;
	private static final String TAG = "fragment";
	private static final String TEXT = "text";
	private TextView mTvContent;
	private String mContent;

	public static ChildScrollerViewFragment newInstance(String content) {
		ChildScrollerViewFragment childFragment = new ChildScrollerViewFragment();
		Bundle args = new Bundle();
		args.putString(TEXT, content);
		childFragment.setArguments(args);
		return childFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (DEBUG) Log.d(TAG, "onCreateView()" + mContent);
		View view = inflater.inflate(R.layout.fragment_scrollview_layout, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		if (DEBUG) Log.d(TAG, "onViewCreated()");
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (DEBUG) Log.d(TAG, "onSaveInstanceState()" + mContent);
		outState.putString(TEXT, mContent);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		if (DEBUG) Log.d(TAG, "onViewStateRestored()" + mContent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (DEBUG) Log.d(TAG, "onDestroy()-->" + mContent);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (DEBUG) Log.d(TAG, "onDestroyView()-->" + mContent);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (DEBUG) Log.d(TAG, "onDetach()-->" + mContent);
	}

	@Override
	public String toString() {
		return "fragment   " + mTvContent;
	}
}
