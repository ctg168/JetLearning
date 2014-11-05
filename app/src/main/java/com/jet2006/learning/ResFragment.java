package com.jet2006.learning;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.BindingListView;
import com.jet2006.entity.BookData;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResFragment extends Fragment {

	private BindingListView bookListView;
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View layoutView = inflater.inflate(R.layout.fragment_resource,
				container, false);

		this.mContext = layoutView.getContext();

		this.bookListView = (BindingListView) layoutView
				.findViewById(R.id.Booklist);

		this.bookListView.setMode(Mode.DISABLED);

		this.bookListView.setItemLayout(R.layout.book_list_item,
				BookData.filed());

		// this.bookListView.setDataSource(new ArrayList<BookData>());

		getBookData();

		return layoutView;
	}

	public void getBookData() {

		String url = "http://192.168.1.66:8588/Book.aspx";

		RequestQueue queue = Volley.newRequestQueue(this.mContext);

		JsonArrayRequest request = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						try {
							Gson gson = new Gson();
							Type listType = new TypeToken<List<BookData>>() {
							}.getType();

							@SuppressWarnings("unchecked")
							List<BookData> datas = (ArrayList<BookData>) gson
									.fromJson(response.toString(), listType);

							BookData localBookData = new BookData();
							localBookData.setBookName("������");
							localBookData.setBookUrl("");
							localBookData.setIsWeb(false);

							datas.add(localBookData);
							bookListView.setDataSource(datas);

							Log.v("Response", response.toString(4));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("Error: ", error.getMessage());
					}
				});

		queue.add(request);
		queue.start();
	}

}
