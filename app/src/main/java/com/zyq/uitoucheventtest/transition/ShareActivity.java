package com.zyq.uitoucheventtest.transition;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zyq.uitoucheventtest.R;

/**
 * @author zyq 15-9-1
 */
public class ShareActivity  extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);

		ImagesAdapter adapter = new ImagesAdapter() {
			@SuppressLint("NewApi")
			@Override
			protected void onClickItem(View v, int position) {
				ViewerActivity.launch(ShareActivity.this, v, data.get(position).resId);
			}
		};

		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);
	}
}
