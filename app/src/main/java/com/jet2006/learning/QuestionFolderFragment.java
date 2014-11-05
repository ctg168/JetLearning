package com.jet2006.learning;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.BindingListView;
import com.jet2006.converter.FolderTypeConverter;
import com.jet2006.entity.FolderData;

public class QuestionFolderFragment extends Fragment {

    private BindingListView folderListVeiw;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View layoutView = inflater.inflate(
                R.layout.fragment_question_folder, container, false);

        this.mContext = layoutView.getContext();

        this.folderListVeiw = (BindingListView) layoutView
                .findViewById(R.id.folderlist);

        this.folderListVeiw.setMode(Mode.DISABLED);

        // initFolderList();

        // 设置ListView的Item的布局
        this.folderListVeiw.setItemLayout(R.layout.folder_list_item,
                FolderData.filed());

        // 添加ListView的Item内指定控件的自定义转换器
        this.folderListVeiw.addValueConverter(R.id.FolderName,
                new FolderTypeConverter());

        // 设置ListView的Item内指定控件的Click事件
        this.folderListVeiw.setOnClickListener(R.id.folderselected,
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(layoutView.getContext(), "你点击了学习按钮",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        // 设置数据源
        // this.folderListVeiw.setDataSource(getFolderData(0, null));

        // 设置ListView的Item的Click事件
        this.folderListVeiw.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;
                HashMap<String, Object> map = (HashMap<String, Object>) listView
                        .getItemAtPosition(position);

                FolderData data = new FolderData();
                data.setFolderId(Integer.parseInt(map.get("FolderId")
                        .toString()));
                data.setParentId(Integer.parseInt(map.get("ParentId")
                        .toString()));
                data.setFolderName(map.get("FolderName").toString());

                // 加载选中节点的子节点
                getFolderData(position == 1 ? data.getParentId() : data
                        .getFolderId());
            }
        });

        // 加载最根级的文件夹
        this.getFolderData(0);

        return layoutView;
    }

    public void getFolderData(final int parentId) {
        String url = "http://192.168.1.66:8588/Data.aspx?id="
                + Integer.toString(parentId);

        final List<FolderData> folderListArray = new ArrayList<FolderData>();

        if (parentId == 0) {
            FolderData rootData = new FolderData();
            rootData.setFolderId(0);
            rootData.setParentId(0);
            rootData.setFolderName("目录");
            folderListArray.add(rootData);
        }

        RequestQueue queue = Volley.newRequestQueue(this.mContext);

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson gson = new Gson();
                            Type listType = new TypeToken<List<FolderData>>() {
                            }.getType();

                            List<FolderData> folders = (ArrayList<FolderData>) gson
                                    .fromJson(response.toString(), listType);

                            if (folders.size() > 0) {
                                folderListArray.addAll(folders);

                                FolderData firstFolder = folderListArray.get(0);
                                if (firstFolder.getFolderId() != firstFolder
                                        .getParentId()
                                        && firstFolder.getFolderId() == parentId) {
                                    firstFolder.setIsRoot(true);
                                }

                                folderListVeiw.setDataSource(folderListArray);
                            }

                            Log.v("Response", response.toString(4));
                        } catch (JSONException e) {
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
    // public interface ErrorListener {
    // /**
    // * Callback method that an error has been occurred with the
    // * provided error code and optional user-readable message.
    // */
    // public void onErrorResponse(VolleyError error);
    // }

    // public ArrayList<FolderData> getData(String parentId) {
    // ArrayList<FolderData> datalist = new ArrayList<FolderData>();
    //
    // FolderData selfData = new FolderData("-1", "", "目录");
    //
    // for (FolderData folderData : this.folderList) {
    // folderData.setPosition(0);
    // if (folderData.getParentid() == parentId) {
    // datalist.add(folderData);
    // }
    //
    // if (folderData.getFolderid() == parentId) {
    // selfData = folderData;
    // }
    // }
    //
    // if (datalist.size() > 0) {
    // selfData.setPosition(-1);
    // datalist.add(0, selfData);
    // }
    //
    // return datalist;
    // }

    // public void initFolderList() {
    //
    // for (int i = 0; i < 10; i++) {
    //
    // FolderData folder1 = new FolderData("id-" + Integer.toString(i),
    // "-1", "文件夹-" + Integer.toString(i));
    // this.folderList.add(folder1);
    //
    // for (int j = 0; j < 10; j++) {
    // FolderData folder2 = new FolderData(folder1.getFolderid() + "-"
    // + Integer.toString(j), folder1.getFolderid(),
    // folder1.getFoldername() + "-" + Integer.toString(j));
    // this.folderList.add(folder2);
    //
    // for (int k = 0; k < 20; k++) {
    //
    // FolderData folder3 = new FolderData(folder2.getFolderid()
    // + "-" + Integer.toString(k), folder2.getFolderid(),
    // folder2.getFoldername() + "-" + Integer.toString(k));
    // this.folderList.add(folder3);
    // }
    // }
    // }
    // }
}
