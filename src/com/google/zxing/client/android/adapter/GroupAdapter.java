package com.google.zxing.client.android.adapter;

import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.callback.GroupCallback;
import com.google.zxing.client.android.model.Group;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GroupAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Group> mGroupsList;
	private LayoutInflater mInflater;
	private GroupCallback mGroupCallback;

	public GroupAdapter(Context context, ArrayList<Group> group,GroupCallback GroupCallback ) {
		super();
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mGroupsList = group;
		mGroupCallback = GroupCallback;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mGroupsList.size();
	}

	@Override
	public Group getItem(int position) {
		// TODO Auto-generated method stub
		return mGroupsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mGroupsList.get(position).getId();
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder vh;
		if (convertView == null) {
			View view = mInflater.inflate(R.layout.group_items, parent, false);
			vh = ViewHolder.create((RelativeLayout) view);
			view.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		Group entry = getItem(position);
		if (entry != null) {
			vh.tvGroup.setText(entry.getName());
			Picasso.with(mContext).load(entry.getImgUrl()).error(R.drawable.launcher_icon).into(vh.imgGroup);
			vh.coverGroup.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mGroupCallback.onClickGroup(position);
				}
			});
		}

		return vh.rootView;
	}

	private static class ViewHolder {
		public final RelativeLayout rootView;
		public final ImageView imgGroup;
		public final TextView tvGroup;
		public final View coverGroup;

		private ViewHolder(RelativeLayout rootView, ImageView imgGroup, TextView tvGroup, View coverGroup) {
			this.rootView = rootView;
			this.imgGroup = imgGroup;
			this.tvGroup = tvGroup;
			this.coverGroup = coverGroup;
		}

		public static ViewHolder create(RelativeLayout rootView) {
			ImageView imgGroup = (ImageView) rootView.findViewById(R.id.img_group);
			TextView tvGroup = (TextView) rootView.findViewById(R.id.tv_group);
			View coverGroup = rootView.findViewById(R.id.cover_group);
			return new ViewHolder(rootView, imgGroup, tvGroup,coverGroup);
		}
	}
}
