package com.jet2006.controls;

import android.content.Context;
import android.util.AttributeSet;

public class CornerListView extends BindingListView {

	public CornerListView(Context context) {
		super(context);
	}

	public CornerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}
/*
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		// switch (ev.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// int x = (int) ev.getX();
		// int y = (int) ev.getY();
		//
		// int itemnum = pointToPosition(x, y);
		// if (itemnum != AdapterView.INVALID_POSITION) {
		// if (itemnum == 0) {
		// if (itemnum == (getAdapter().getCount() - 1)) {
		//
		// setSelector(R.drawable.app_list_corner_round);
		// } else {
		// setSelector(R.drawable.app_list_corner_round_top);
		// }
		// } else if (itemnum == (getAdapter().getCount() - 1)) {
		// setSelector(R.drawable.app_list_corner_round_bottom);
		// } else {
		// setSelector(R.drawable.app_list_corner_round_center);
		// }
		// }
		// break;
		// default:
		// break;
		// }

		return super.onInterceptTouchEvent(ev);
	}*/
}
