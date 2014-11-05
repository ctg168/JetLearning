package com.jet2006.learning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.jet2006.terry.jetlearning.R;

public class StartActivity extends FragmentActivity  {

	private static final int TIMER = 3500;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		new AsyncTask<Void, Void, Integer>() {
			@Override
			protected Integer doInBackground(Void...params) {//�ɱ��������
				try {
					Thread.sleep(TIMER);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				return null;
			};
			
			@Override
			protected void onPostExecute(Integer result) {
				Intent  intent = new Intent();
				intent.setClass(StartActivity.this, LoginActivity.class);

				startActivity(intent);
				StartActivity.this.finish();
				
			};
				
			
		}.execute(new Void[]{});

	}
}
