package com.jet2006.converter;

import android.view.View;

public interface IValueConverter {
	void Convert(View view, Object parameter, Object targetType);
}
