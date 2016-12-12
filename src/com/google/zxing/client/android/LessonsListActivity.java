package com.google.zxing.client.android;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.client.android.adapter.LessonAdapter;
import com.google.zxing.client.android.callback.LessonCallback;
import com.google.zxing.client.android.model.Lesson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

public class LessonsListActivity extends Activity implements LessonCallback {
	private ArrayList<Lesson> mLessonsList;
	private LessonAdapter mLessonAdapter; 
	private GridView mGvLessons;
	private ProgressDialog mProgressDialog;
	private int mCurrentUserId = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lesson_list_activity);
		if (User.getInstance(this) != null) {
			mCurrentUserId =  User.getInstance().getId();
		}
		mGvLessons = (GridView)findViewById(R.id.gv_lesson);
		mLessonsList = new ArrayList<Lesson>();
		mLessonAdapter = new LessonAdapter(this, mLessonsList,this);
		mGvLessons.setAdapter(mLessonAdapter);
		initProgressBar();
		initData();
	}

	private void initData() {
		if (mProgressDialog != null && !mProgressDialog.isShowing()) {
			mProgressDialog.show();
		}

		final String url = HttpHelper.TAG_HOST + HttpHelper.TAG_LESSON;
		final List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("studentid", String.valueOf(mCurrentUserId)));
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				String response = HttpHelper.makeServerCall(url, HttpHelper.GET, param);
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
										Lesson lesson = getLessonFromJSon(resultList.getJSONObject(i));
										mLessonsList.add(lesson);
									}
									if (mLessonAdapter != null) {
										mLessonAdapter.notifyDataSetChanged();
									}
								}
								sendHandlerMessage(100);
							} else {
								sendHandlerMessage(102);
							}
						} else {
							sendHandlerMessage(102);
						}
						Log.e("ALL_LESSON", jsonObj.toString());

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		thread.start();
		
	}
	
	private Lesson getLessonFromJSon(JSONObject data){
		Lesson lesson = new Lesson();
		try {
			int id = data.getInt("Id");
			String name= data.getString("Name");
			String imageId = data.getString("ImageId");
			String imageUrl = data.getString("ImageUrl");
			lesson.setId(id);
			lesson.setName(name);
			lesson.setImgUrl(HttpHelper.TAG_HOST+imageUrl);
			lesson.setState(true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesson;
	}
	
	private void dismissDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
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
				Toast.makeText(LessonsListActivity.this, "Error", Toast.LENGTH_LONG).show();;
			}
		};
	};
	
	@Override
	public void onClickLesson(int pos) {
		Lesson entry  = mLessonsList.get(pos);
		goToGroup(entry);
	}
	
	private void goToGroup(Lesson lesson){
		Intent intent = new Intent(this, GroupListActivity.class);
		intent.putExtra(Utils.LESSON_CONTENT, lesson);
		startActivity(intent);
	}
	
	private void initProgressBar(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Getting lessons");
        mProgressDialog.setMessage("Please wait..");
    }
}
