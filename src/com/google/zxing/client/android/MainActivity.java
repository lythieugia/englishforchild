package com.google.zxing.client.android;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.User;

public class MainActivity extends Activity {

	public int SCANNER_REQUEST_CODE = 123;

	private Button btnScan;
	private Button btnGen;
	private Button btnLogout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		useEnglishDefault();
		initViews();
	}

	private void initViews() {
		
		btnScan = (Button) findViewById(R.id.btnScan);
		btnScan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent scanmode = new Intent(MainActivity.this, CaptureActivity.class);
				scanmode.putExtra("SCAN_MODE", "SCAN_MODE");
				startActivityForResult(scanmode, SCANNER_REQUEST_CODE);
			}
		});
		
		
		
		btnLogout = (Button) findViewById(R.id.btnLogOut);
		btnLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				User.removeUserLocal(MainActivity.this);
				Intent intent = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	private void useEnglishDefault() {
		// change language by onclick a button
        Configuration newConfig = new Configuration();
        newConfig.locale = Locale.ENGLISH;
        onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == SCANNER_REQUEST_CODE) {
			// Handle scan intent
			if (resultCode == Activity.RESULT_OK) {
				// Handle successful scan
//				String contents = intent.getStringExtra("SCAN_RESULT");
//				String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
//				byte[] rawBytes = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
//				int intentOrientation = intent.getIntExtra("SCAN_RESULT_ORIENTATION", Integer.MIN_VALUE);
//				Integer orientation = (intentOrientation == Integer.MIN_VALUE) ? null : intentOrientation;
//				String errorCorrectionLevel = intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL");

				

			} else if (resultCode == Activity.RESULT_CANCELED) {
				// Handle cancel
			}
		} else {
			// Handle other intents
		}

	}
}
