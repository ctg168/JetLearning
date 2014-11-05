package com.jet2006.learning;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jet2006.terry.jetlearning.R;

public class SearchFragment extends DialogFragment {

	private View layoutView;
	private ImageView backImageView;
	private ImageView searchImageView;

	@Override
	public void onStart() {

		if (getDialog() == null) {
			return;
		}

		getDialog().getWindow().setWindowAnimations(R.style.dialog_animation);

		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		setStyle(DialogFragment.STYLE_NO_FRAME,
				android.R.style.Theme_Black_NoTitleBar_Fullscreen);

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		this.layoutView = inflater.inflate(R.layout.fragment_search, container,
				false);

		this.backImageView = (ImageView) layoutView.findViewById(R.id.back);
		this.searchImageView = (ImageView) layoutView.findViewById(R.id.search);

		this.backImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		this.searchImageView.setOnClickListener(new OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {

				String sContent = ((EditText) layoutView
						.findViewById(R.id.searchContent)).getText().toString();

				if (sContent.equals("")) {
					Toast.makeText(layoutView.getContext(), "��������������������",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		return layoutView;
	}
}
