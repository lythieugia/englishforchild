package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Handler handler = new Handler();

		final Runnable r = new Runnable() {
			public void run() {

				Intent i = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(i);
				finish();
			}
		};
		handler.postDelayed(r, 2000);
	}
}
