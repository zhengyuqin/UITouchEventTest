package com.zyq.uitoucheventtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyq 15-7-20
 */
public class ChildListViewFragment extends Fragment {

	private static final boolean DEBUG = true;
	private static final String TAG = "fragment";
	private static final String TEXT = "text";
	private String mContent;
	private ListAdapter mListAdapter;
	private List<String> mList;
	private ListView mListView;

	public static ChildListViewFragment newInstance(String content) {
		ChildListViewFragment childFragment = new ChildListViewFragment();
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
		mList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			mList.add("text" + String.valueOf(i));
		}
		mListAdapter = new ListAdapter(getActivity(), mList);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (DEBUG) Log.d(TAG, "onCreateView()" + mContent);
		View view = inflater.inflate(R.layout.fragment_listview_layout, null);
		mListView = (ListView) view.findViewById(R.id.list);
		mListView.setAdapter(mListAdapter);
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


}
