package com.google.zxing.client.android.adapter;

import java.util.ArrayList;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.callback.LessonCallback;
import com.google.zxing.client.android.model.Lesson;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LessonAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Lesson> mLessonsList;
	private LayoutInflater mInflater;
	private LessonCallback mLessonCallback;

	public LessonAdapter(Context context, ArrayList<Lesson> lesson,LessonCallback lessonCallback ) {
		super();
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mLessonsList = lesson;
		mLessonCallback = lessonCallback;
	}

	@Override
	public int getCount() {
		return mLessonsList.size();
	}

	@Override
	public Lesson getItem(int position) {
		return mLessonsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return mLessonsList.get(position).getId();
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder vh;
		if (convertView == null) {
			View view = mInflater.inflate(R.layout.lesson_items, parent, false);
			vh = ViewHolder.create((RelativeLayout) view);
			view.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		Lesson entry = getItem(position);
		if (entry != null) {
			vh.tvLesson.setText(entry.getName());
			Picasso.with(mContext).load(entry.getImgUrl()).error(R.drawable.launcher_icon).into(vh.imgLesson);
			vh.coverLesson.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mLessonCallback.onClickLesson(position);
				}
			});
		}

		return vh.rootView;
	}

	private static class ViewHolder {
		public final RelativeLayout rootView;
		public final ImageView imgLesson;
		public final TextView tvLesson;
		public final View coverLesson;

		private ViewHolder(RelativeLayout rootView, ImageView imgLesson, TextView tvLesson, View coverLesson) {
			this.rootView = rootView;
			this.imgLesson = imgLesson;
			this.tvLesson = tvLesson;
			this.coverLesson = coverLesson;
		}

		public static ViewHolder create(RelativeLayout rootView) {
			ImageView imgLesson = (ImageView) rootView.findViewById(R.id.img_lesson);
			TextView tvLesson = (TextView) rootView.findViewById(R.id.tv_lesson);
			View coverLesson = rootView.findViewById(R.id.cover_lesson);
			return new ViewHolder(rootView, imgLesson, tvLesson,coverLesson);
		}
	}
}
