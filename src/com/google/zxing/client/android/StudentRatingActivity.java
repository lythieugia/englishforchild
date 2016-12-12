package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class StudentRatingActivity extends Activity implements OnClickListener {
	private CheckBox cbLike;
	private View viewCoverLike;
	private CheckBox cbUnlike;
	private View viewCoverUnlike;
	private Button btnOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		findViews();
	}

	/**
	 * Find the Views in the layout<br />
	 * <br />
	 * Auto-created on 2016-11-22 15:25:01 by Android Layout Finder
	 * (http://www.buzzingandroid.com/tools/android-layout-finder)
	 */
	private void findViews() {
		cbLike = (CheckBox) findViewById(R.id.cb_like);
		cbLike.setChecked(true);
		viewCoverLike = (View) findViewById(R.id.view_cover_like);
		viewCoverLike.setOnClickListener(this);
		cbUnlike = (CheckBox) findViewById(R.id.cb_unlike);
		cbUnlike.setChecked(false);
		viewCoverUnlike = (View) findViewById(R.id.view_cover_unlike);
		viewCoverUnlike.setOnClickListener(this);
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_cover_like: 
		{	
			cbLike.setChecked(true);
			cbUnlike.setChecked(false);
		}
			break;
		case R.id.view_cover_unlike: 
		{	
			cbLike.setChecked(false);
			cbUnlike.setChecked(true);
		}
			break;
		case R.id.btnOK: 
		{
			gotoLesson();
		}
			break;
		default:
			break;
		}
	}
	
	private void gotoLesson(){
		Intent i = new Intent(this,LessonsListActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}

}
