package com.jet2006.learning;

//http://my.oschina.net/freestyletime/blog/71544
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.jet2006.terry.jetlearning.R;

public class SettingActivity extends PreferenceActivity {
	private PreferenceScreen preferenceScreen;
	private ImagePreference pre_update;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initPreference();
	}

	@SuppressWarnings("deprecation")
	private void initPreference() {
		System.out.println("aaa");
		addPreferencesFromResource(R.xml.app_settings);
		preferenceScreen = getPreferenceScreen();
		preferenceScreen.setOrderingAsAdded(true);
		this.getListView().setBackgroundColor(
				getResources().getColor(R.color.jet_background_deep_01));

		initImagePreference();

		preferenceScreen.addPreference(pre_update);
	}

	private void initImagePreference() {
		pre_update = new ImagePreference(this,
				R.drawable.preference_versionupdate);
		pre_update.setKey("UPDATE");
		pre_update.setTitle("abc");
	}

	public class ImagePreference extends Preference {
		private ImageView iv_perference_icon;
		private int _id;
		private OnClickListener mOnClickListener;

		public ImagePreference(Context context, int id) {
			super(context);
			this._id = id;
			setLayoutResource(R.layout.preference_list_item_layout);
		}

		@Override
		protected void onBindView(View view) {
			super.onBindView(view);
			iv_perference_icon = (ImageView) view.findViewById(R.id.iv_perference_icon);
			iv_perference_icon.setImageResource(_id);
			view.setClickable(true);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				}
			});
		}
	}
}
