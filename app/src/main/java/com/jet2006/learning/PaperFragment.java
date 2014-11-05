package com.jet2006.learning;

import java.util.ArrayList;
import java.util.List;


import android.R.integer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jet2006.terry.jetlearning.R;


public class PaperFragment extends Fragment {



	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View layoutView = inflater.inflate(R.layout.fragment_paper, container,
				false);



		return layoutView;

	}


}
