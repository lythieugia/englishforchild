package com.google.zxing.client.android;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.client.android.adapter.GroupAdapter;
import com.google.zxing.client.android.callback.GroupCallback;
import com.google.zxing.client.android.model.Group;
import com.google.zxing.client.android.model.Lesson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class GroupListActivity extends Activity implements GroupCallback {
	private ArrayList<Group> mGroupsList;
	private GroupAdapter mGroupsAdapter; 
	private GridView mGvGroups;
	private ProgressDialog mProgressDialog;
	private Lesson mLesson;
	private int mLessonId = -1;
	private Button btnRate;
	private int mCurrentUserId = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extra = getIntent().getExtras();
		if (extra != null) {
			mLesson = (Lesson) extra.getSerializable(Utils.LESSON_CONTENT);
			if (mLesson != null && Utils.textIsNotEmpty(mLesson.getName())) {
				this.setTitle(mLesson.getName());
			}
			mLessonId = mLesson.getId();
		}
		if (User.getInstance(this) != null) {
			mCurrentUserId =  User.getInstance().getId();
		}
		setContentView(R.layout.group_list_activity);
		mGvGroups = (GridView)findViewById(R.id.gv_group);
		btnRate = (Button) findViewById(R.id.btn_rating);
		btnRate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoRating();
			}
		});
		mGroupsList = new ArrayList<Group>();
		mGroupsAdapter = new GroupAdapter(this, mGroupsList,this);
		mGvGroups.setAdapter(mGroupsAdapter);
		initProgressBar();
		initData();
	}

	private void gotoScan(Group group){
		Intent i = new Intent(this, CaptureActivity.class);
		i.putExtra(Utils.GROUP_CONTENT, group);
		startActivity(i);
	}
	
	private void gotoRating(){
		Intent i = new Intent(this, StudentRatingActivity.class);
		startActivity(i);
	}
	
	private void initData() {
		if (mProgressDialog != null && !mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}
		final String url = HttpHelper.TAG_HOST + HttpHelper.TAG_GROUP;
		final List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("studentid", String.valueOf(mCurrentUserId)));
		Log.e("ALL_GROUP", "student id"+mCurrentUserId);
		param.add(new BasicNameValuePair("lessonId", String.valueOf(mLessonId)));
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				String response = HttpHelper.makeServerCall(url, HttpHelper.GET, param);
				Log.e("ALL_GROUP", response);
				if (response != null) {
					try {
						dismissDialog();
						JSONObject jsonObj = new JSONObject(response);
						if (jsonObj.has("ok") && !jsonObj.isNull("ok")) {
							Boolean ok = jsonObj.getBoolean("ok");
							if (ok) {
								if (jsonObj.has("result") && !jsonObj.isNull("result")) {
									JSONArray resultList = jsonObj.getJSONArray("result");
									for(int i = 0 ; i < resultList.length();i++){
										JSONObject groupObject = resultList.getJSONObject(i).getJSONObject("Group");
										try {
											Group group = getGroupFromJSon(groupObject);
											mGroupsList.add(group);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									if (mGroupsAdapter != null) {
										mGroupsAdapter.notifyDataSetChanged();
									}
								}
								sendHandlerMessage(100);
							} else {
								sendHandlerMessage(102);
							}
						} else {
							sendHandlerMessage(102);
						}
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		thread.start();
	}
	
	private Group getGroupFromJSon(JSONObject data){
		Group group = new Group();
		try {
			int id = data.getInt("Id");
			String name= data.getString("Name");
			String imageId = data.getString("ImageId");
			String imageUrl = data.getString("ImageUrl");
			group.setId(id);
			group.setName(name);
			group.setImgUrl(HttpHelper.TAG_HOST+imageUrl);
			group.setState(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;
	}
	
	private void sendHandlerMessage(int code){
		Message msg = handler.obtainMessage();
		msg.what = code;
		handler.sendMessage(msg);
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			
			if(msg.what == 100){
				
				
			} else if (msg.what == 102) {
				Toast.makeText(GroupListActivity.this, "Error", Toast.LENGTH_LONG).show();;
			}
		};
	};
	
	private void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}
	
	private void initProgressBar(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Getting chapters");
        mProgressDialog.setMessage("Please wait..");
    }

	@Override
	public void onClickGroup(int pos) {
		Group entry  = mGroupsList.get(pos);
		gotoScan(entry);
	}

	
}
