package com.jet2006.converter;

import java.util.Map;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.jet2006.terry.jetlearning.R;


public class FolderTypeConverter implements IValueConverter {

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void Convert(View view, Object parameter, Object targetType) {
		TextView v = (TextView) view;
		Map data = (Map) targetType;

		Boolean isRoot = Boolean.parseBoolean(data.get("IsRoot").toString());

		Drawable drawable;
		if (isRoot) {
			drawable = view.getResources().getDrawable(R.drawable.swapleft);
		} else {
			drawable = view.getResources().getDrawable(R.drawable.folder);
		}

		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		v.setCompoundDrawables(drawable, null, null, null);
		v.setText(parameter.toString());
	}
}
