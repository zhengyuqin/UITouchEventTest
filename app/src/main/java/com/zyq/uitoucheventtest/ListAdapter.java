package com.zyq.uitoucheventtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author zyq 15-8-28
 */
public class ListAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mListData;

	public ListAdapter(Context context, List<String> data) {
		mContext = context;
		mListData = data;
	}

	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_layout, parent, false);
			holder.mContent = (TextView) convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mContent.setText(mListData.get(position).toString());
		return convertView;
	}

	class ViewHolder {
		private TextView mContent;
	}
}
