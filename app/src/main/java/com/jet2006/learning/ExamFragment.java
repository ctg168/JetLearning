package com.jet2006.learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jet2006.terry.jetlearning.R;

public class ExamFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View layoutView = inflater.inflate(R.layout.fragment_exam, container,
				false);

		return layoutView;

	}

}
