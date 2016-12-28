package com.google.zxing.client.android;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class StudentRatingActivity extends Activity implements OnClickListener {
	private CheckBox cbLike;
	private View viewCoverLike;
	private CheckBox cbUnlike;
	private View viewCoverUnlike;
	private Button btnOK;
	private int mCurrentUserId = -1;
	private int mLessonId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		if (User.getInstance(this) != null) {
			mCurrentUserId =  User.getInstance().getId();
		}
		Bundle extra = getIntent().getExtras();
		mLessonId = extra.getInt(Utils.LESSON_ID,-1);
		findViews();
	}

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
			int feeling = 1;
			if(cbLike.isChecked()){
				feeling = 1;
			} else {
				feeling = 0;
			}
			sendFeedback(mCurrentUserId, mLessonId, feeling);
		}
			break;
		default:
			break;
		}
	}
	
	private void sendFeedback(final int studentId,final int lessonId,final int feeling){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				StudentFeedback postData = new StudentFeedback(studentId,lessonId,feeling);
				String postMessage = new Gson().toJson(postData);
				Log.e("RESULT_FEEDBACK", "postMessage: " + postMessage);
				try {
					int TIMEOUT_MILLISEC = 10000; // = 10 seconds
					HttpParams httpParams = new BasicHttpParams();
					HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
					HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
					HttpClient client = new DefaultHttpClient(httpParams);
					String serverUrl = HttpHelper.TAG_HOST + HttpHelper.TAG_SEND_FEEDBACKS;
					HttpPost request = new HttpPost(serverUrl);
					StringEntity se = new StringEntity(postMessage.toString());
					se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					request.setEntity(se);
					HttpResponse response = client.execute(request);
					String resultResponse = EntityUtils.toString(response.getEntity());
					Log.e("RESULT_UPDATE", "response: " + resultResponse);
					JSONObject jsonObj = new JSONObject(resultResponse);
					if (jsonObj.has("ok") && !jsonObj.isNull("ok")) {
						Boolean ok = jsonObj.getBoolean("ok");
						if (ok) {
							Log.e("RESULT_UPDATE", "ok: " + ok);
							sendHandlerMessage(HttpHelper.UPDATED_RESULT);
						}
					}
					
				} catch (Exception e) {
					
				}
			}
		});
		thread.start();
	}
	
	private void sendHandlerMessage(int code){
		Message msg = handler.obtainMessage();
		msg.what = code;
		handler.sendMessage(msg);
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == HttpHelper.UPDATED_RESULT){
				Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();
				finish();
			} 
		};
	};
	
	private void gotoLesson(){
		Intent i = new Intent(this,LessonsListActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}

}
