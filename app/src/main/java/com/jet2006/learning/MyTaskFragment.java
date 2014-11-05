package com.jet2006.learning;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.jet2006.terry.jetlearning.R;

public class MyTaskFragment extends Fragment {

	// private static MyTaskActivity instance = null;

	public MyTaskFragment() {
	}

	// public static MyTaskActivity getInstance() {
	// if (instance == null) {
	// instance = new MyTaskActivity();
	// }
	// return instance;
	// }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_my_task, container, false);
	}

}
