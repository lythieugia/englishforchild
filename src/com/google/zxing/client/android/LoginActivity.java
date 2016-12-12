package com.google.zxing.client.android;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;;

public class LoginActivity extends Activity {

	private Button btnLogin;
	private EditText edtName;
	private int mId;
	private String mName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		if (User.getInstance(this) != null) {
			Log.e("CURRENTLOGIN", "curent "+ User.getInstance().getName());
			Intent i = new Intent(LoginActivity.this, LessonsListActivity.class);
			startActivity(i);
			finish();
		}
		btnLogin = (Button) findViewById(R.id.btn_login);
		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String account = edtName.getText().toString();
				if (Utils.textIsNotEmpty(account)) {
					login(account);
				}
			}
		});
		edtName = (EditText) findViewById(R.id.edt_login);
	}

	private void login(String name) {
		final String url = HttpHelper.TAG_HOST + HttpHelper.TAG_LOGIN;
		final List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("name", name.trim()));
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				String response = HttpHelper.makeServerCall(url, HttpHelper.GET, param);
				if (response != null) {
					try {
						JSONObject jsonObj = new JSONObject(response);
						if (jsonObj.has("ok") && !jsonObj.isNull("ok")) {
							Boolean ok = jsonObj.getBoolean("ok");
							if (ok) {
								if (jsonObj.has("result") && !jsonObj.isNull("result")) {
									JSONObject result = jsonObj.getJSONObject("result");
									mId = result.getInt("studentId");
									mName = result.getString("studentName");
								}
								sendHandlerMessage(100);
							} else {
								sendHandlerMessage(102);
							}
						} else {
							sendHandlerMessage(102);
						}
						Log.e("USER_LOGIN", jsonObj.toString());

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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
			if(msg.what == 100){
				Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_LONG).show();
				User.saveUser2Local(LoginActivity.this, mId, mName);
				Intent i = new Intent(LoginActivity.this, LessonsListActivity.class);
				startActivity(i);
				finish();
			} else if (msg.what == 102) {
				Toast.makeText(LoginActivity.this, "login fail", Toast.LENGTH_LONG).show();;
			}
		};
	};
}
