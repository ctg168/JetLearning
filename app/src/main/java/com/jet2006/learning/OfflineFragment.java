package com.jet2006.learning;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.DownLoadItemView;
import com.jet2006.entity.DownLoadData;

public class OfflineFragment extends Fragment {

	private ListView downLoadListView;
	private ArrayList<DownLoadData> mDatas;
	private DownLoadAdapter mDownLoadAdapter;

	private Button startButton;
	private Button pauseButton;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View layoutView = inflater.inflate(R.layout.fragment_offline,
				container, false);

		this.downLoadListView = (ListView) layoutView
				.findViewById(R.id.downloadlist);

		this.startButton = (Button) layoutView.findViewById(R.id.startDownload);
		this.pauseButton = (Button) layoutView.findViewById(R.id.pauseDownload);

		this.mDatas = new ArrayList<DownLoadData>();

		this.mDownLoadAdapter = new DownLoadAdapter(layoutView.getContext(),
				this.mDatas);

		this.downLoadListView.setAdapter(mDownLoadAdapter);

		initDownloadData();

		this.startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < downLoadListView.getChildCount(); i++) {
					DownLoadItemView view = (DownLoadItemView) downLoadListView
							.getChildAt(i);
					view.Start();
				}
			}
		});

		this.pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i < downLoadListView.getChildCount(); i++) {
					DownLoadItemView view = (DownLoadItemView) downLoadListView
							.getChildAt(i);
					view.Pause();
				}
			}
		});

		return layoutView;
	}

	public void addDownloadTask(DownLoadData data) {
		this.mDatas.add(0, data);
		this.mDownLoadAdapter.notifyDataSetChanged();
	}

	private void initDownloadData() {

		for (int i = 0; i < 8; i++) {
			DownLoadData data1 = new DownLoadData();
			data1.setName(Integer.toString(i + 1) + ".jpg");
			this.addDownloadTask(data1);
		}
	}

	public class DownLoadAdapter extends BaseAdapter {

		private Context mContext;
		private List<DownLoadData> mDatas;

		public DownLoadAdapter(Context context, List<DownLoadData> datas) {
			this.mContext = context;
			this.mDatas = datas;
		}

		@Override
		public int getCount() {
			return this.mDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return this.mDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final DownLoadData itemData = (DownLoadData) getItem(position);
			View v;
			if (convertView == null) {
				v = new DownLoadItemView(this.mContext, itemData);
			} else {
				v = convertView;
			}

			return v;
		}
	}

}
