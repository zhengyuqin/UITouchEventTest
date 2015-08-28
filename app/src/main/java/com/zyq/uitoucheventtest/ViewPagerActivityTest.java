package com.zyq.uitoucheventtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyq 15-8-28
 */
public class ViewPagerActivityTest extends ActionBarActivity {

	private List<Fragment> mChildFragments;
	private ChildFragmentStatePager mAdapter;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		mViewPager = (ViewPager) findViewById(R.id.vp_layout_content);
		mChildFragments = new ArrayList<>();
		mChildFragments.add(new ChildScrollerViewFragment());
		mChildFragments.add(new ChildListViewFragment());
		mChildFragments.add(new ChildScrollerViewFragment());
		mAdapter = new ChildFragmentStatePager(getSupportFragmentManager(), mChildFragments);
		mViewPager.setAdapter(mAdapter);
	}
}
