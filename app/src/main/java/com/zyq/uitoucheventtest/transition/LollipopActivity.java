package com.zyq.uitoucheventtest.transition;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zyq.uitoucheventtest.R;

/**
 * @author zyq 15-9-1
 */
public class LollipopActivity extends ListActivity {

	/**
	 * This class describes an individual sample (the sample title, and the activity class that
	 * demonstrates this sample).
	 */
	private class Sample {
		private CharSequence title;
		private Class<? extends Activity> activityClass;

		public Sample(int titleResId, Class<? extends Activity> activityClass) {
			this.activityClass = activityClass;
			this.title = getResources().getString(titleResId);
		}

		@Override
		public String toString() {
			return title.toString();
		}
	}

	/**
	 * The collection of all samples in the app. This gets instantiated in {@link
	 * #onCreate(Bundle)} because the {@link Sample} constructor needs access to {@link
	 * android.content.res.Resources}.
	 */
	private static Sample[] mSamples;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_animator);

		// Instantiate the list of samples.
		mSamples = new Sample[]{
				new Sample(R.string.touch_feedback, TouchActivity.class),
				new Sample(R.string.reveal_effect, RevealActivity.class),
				new Sample(R.string.activity_transition, ActivityA.class),
				new Sample(R.string.activity_share, ShareActivity.class),
		};

		setListAdapter(new ArrayAdapter<Sample>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				mSamples));
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		// Launch the sample associated with this list position.
		startActivity(new Intent(LollipopActivity.this, mSamples[position].activityClass));
	}
}