package com.jet2006.learning;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.BindingListView;
import com.jet2006.entity.LearningData;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.text.format.DateUtils;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionFragment extends Fragment implements
        OnRefreshListener2<ListView>, OnKeyListener {

    // private PullToRefreshListView pullListView;
    // private ArrayList<HashMap<String, Object>> dataList;
    // private SimpleAdapter listAdapter;
    // private ArrayList<HashMap<String, Object>> dataBase;

    private BindingListView pullListView;
    private ArrayList<LearningData> dataBase;

    private DrawerLayout drawerLayout;
    private ImageView listMenuImageView;
    private ImageView searchImageView;

    private int dataIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container,
                false);

        // this.pullListView = (PullToRefreshListView) view
        // .findViewById(R.id.qlistview);

        this.pullListView = (BindingListView) view.findViewById(R.id.qlistview);

        this.drawerLayout = (DrawerLayout) view
                .findViewById(R.id.drawer_layout);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.left_menu, new QuestionFolderFragment()).commit();

        this.listMenuImageView = (ImageView) view.findViewById(R.id.listmenu);
        this.searchImageView = (ImageView) view.findViewById(R.id.search);

        this.pullListView.setMode(Mode.BOTH);
        this.pullListView.setOnRefreshListener(QuestionFragment.this);

        // this.dataBase = new ArrayList<HashMap<String, Object>>();
        // this.dataList = new ArrayList<HashMap<String, Object>>();
        //
        // String[] from = new String[] { "learningname", "learninginfo",
        // "learningpro" };
        // int[] to = new int[] { R.id.learningname, R.id.learninginfo,
        // R.id.learningpro };
        //
        // this.listAdapter = new SimpleAdapter(view.getContext(), dataList,
        // R.layout.my_learning_info_list_item, from, to);
        //
        // this.pullListView.setAdapter(listAdapter);

        this.dataBase = new ArrayList<LearningData>();
        this.pullListView.setItemLayout(R.layout.my_learning_info_list_item,
                LearningData.field());

        this.pullListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });

        this.listMenuImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        this.searchImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchDialog = new SearchFragment();
                searchDialog.show(getChildFragmentManager(), "");
            }
        });

        TextView tv = new TextView(view.getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setText("正在加载，请稍后...");
        this.pullListView.setEmptyView(tv);

        this.initDataBase();
        new GetDataTask().execute();

        return view;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        Log.i("viewOnkey", Integer.toString(event.getKeyCode()));

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {

                Log.i("backkey", Integer.toString(event.getKeyCode()));

                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

        String str = DateUtils.formatDateTime(getActivity(),
                System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE
                        | DateUtils.FORMAT_NO_NOON);

        refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
        refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);

        dataIndex = 0;

        new GetDataTask().execute();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

        String str = DateUtils.formatDateTime(getActivity(),
                System.currentTimeMillis(), DateUtils.FORMAT_NUMERIC_DATE
                        | DateUtils.FORMAT_NO_NOON);

        refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
        refreshView.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
        refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
        refreshView.getLoadingLayoutProxy()
                .setLastUpdatedLabel("最后更新时间:" + str);

        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            ArrayList<LearningData> itemList = getItems(dataIndex);

            if (dataIndex == 0) {
                pullListView.setDataSource(itemList);
            } else {
                pullListView.addAllData(itemList);
            }

            if (itemList.size() > 0) {
                dataIndex = itemList.get(itemList.size() - 1).getId();
            }

            pullListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private void initDataBase() {
        for (int i = 0; i < 100; i++) {
            LearningData itemData = new LearningData();
            itemData.setId(i);
            itemData.setLearningName("人力资源管理(基础知识)" + Integer.toString(i));
            itemData.setLearningInfo("上次练习到236题");
            itemData.setLearningPro("9-25 13:50");

            dataBase.add(itemData);
        }
    }

    private ArrayList<LearningData> getItems(int index) {
        ArrayList<LearningData> itemList = new ArrayList<LearningData>();
        for (int i = index + 1; i < dataBase.size(); i++) {
            itemList.add(dataBase.get(i));
            if (itemList.size() == 20) {
                break;
            }
        }
        return itemList;
    }
}
