package com.jet2006.learning;

import java.util.ArrayList;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.BindingListView;
import com.jet2006.entity.LearningData;

public class MyFovFragment extends Fragment {

    private BindingListView quesListView = null;
    private BindingListView paperListView = null;
    private BindingListView resourceListView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_fov, container,
                false);

        this.quesListView = (BindingListView) view.findViewById(R.id.quesList);
        this.paperListView = (BindingListView) view.findViewById(R.id.paperList);
        this.resourceListView = (BindingListView) view.findViewById(R.id.resourceList);

        this.quesListView.setItemLayout(R.layout.my_fov_ques_list_item, LearningData.field());
        this.paperListView.setItemLayout(R.layout.my_fov_paper_list_item, LearningData.field());
        this.resourceListView.setItemLayout(R.layout.my_fov_resource_list_item, LearningData.field());

        this.quesListView.setDataSource(getQuesData());
        this.paperListView.setDataSource(getPaperData());
        this.resourceListView.setDataSource(getResData());

        return view;

    }

    public ArrayList<LearningData> getQuesData() {
        ArrayList<LearningData> infoList = new ArrayList<LearningData>();

        LearningData item1 = new LearningData();
        item1.setLearningName("人力资源管理(基础知识)");
        item1.setLearningInfo("236/620");
        item1.setLearningPro("9:00");
        item1.setLearningPic(R.drawable.word);
        infoList.add(item1);

        LearningData item2 = new LearningData();
        item2.setLearningName("巡查员规程讲义");
        item2.setLearningInfo("63/97");
        item2.setLearningPro("7:50");
        item2.setLearningPic(R.drawable.word);
        infoList.add(item2);

        LearningData item3 = new LearningData();
        item3.setLearningName("班组长培训第三集");
        item3.setLearningInfo("115/170");
        item3.setLearningPro("9-25 13:50");
        item3.setLearningPic(R.drawable.word);
        infoList.add(item3);

        LearningData item4 = new LearningData();
        item4.setLearningName("企业文化");
        item4.setLearningInfo("215/370");
        item4.setLearningPro("9-20 13:50");
        item4.setLearningPic(R.drawable.word);
        infoList.add(item4);

        LearningData item5 = new LearningData();
        item5.setLearningName("班组长培训第二集");
        item5.setLearningInfo("10/120");
        item5.setLearningPro("6-25 23:50");
        item5.setLearningPic(R.drawable.word);
        infoList.add(item5);

        LearningData item6 = new LearningData();
        item6.setLearningName("班组长培训第三集");
        item6.setLearningInfo("10/120");
        item6.setLearningPro("6-25 23:50");
        item6.setLearningPic(R.drawable.word);
        infoList.add(item6);

        LearningData item7 = new LearningData();
        item7.setLearningName("班组长培训第四集");
        item7.setLearningInfo("10/120");
        item7.setLearningPro("6-25 23:50");
        item7.setLearningPic(R.drawable.word);
        infoList.add(item7);

        return infoList;
    }

    public ArrayList<LearningData> getPaperData() {
        ArrayList<LearningData> infoList = new ArrayList<LearningData>();

        LearningData item1 = new LearningData();
        item1.setLearningName("人力资源管理(基础知识)");
        item1.setLearningInfo("236/620");
        item1.setLearningPro("9:00");
        item1.setLearningPic(R.drawable.excel);
        infoList.add(item1);

        LearningData item2 = new LearningData();
        item2.setLearningName("巡查员规程讲义");
        item2.setLearningInfo("63/97");
        item2.setLearningPro("7:50");
        item2.setLearningPic(R.drawable.excel);
        infoList.add(item2);

        LearningData item3 = new LearningData();
        item3.setLearningName("班组长培训第三集");
        item3.setLearningInfo("115/170");
        item3.setLearningPro("9-25 13:50");
        item3.setLearningPic(R.drawable.excel);
        infoList.add(item3);

        LearningData item4 = new LearningData();
        item4.setLearningName("企业文化");
        item4.setLearningInfo("215/370");
        item4.setLearningPro("9-20 13:50");
        item4.setLearningPic(R.drawable.excel);
        infoList.add(item4);

        LearningData item5 = new LearningData();
        item5.setLearningName("班组长培训第二集");
        item5.setLearningInfo("10/120");
        item5.setLearningPro("6-25 23:50");
        item5.setLearningPic(R.drawable.excel);
        infoList.add(item5);

        LearningData item6 = new LearningData();
        item6.setLearningName("班组长培训第三集");
        item6.setLearningInfo("10/120");
        item6.setLearningPro("6-25 23:50");
        item6.setLearningPic(R.drawable.excel);
        infoList.add(item6);

        LearningData item7 = new LearningData();
        item7.setLearningName("班组长培训第四集");
        item7.setLearningInfo("10/120");
        item7.setLearningPro("6-25 23:50");
        item7.setLearningPic(R.drawable.excel);
        infoList.add(item7);

        return infoList;
    }

    public ArrayList<LearningData> getResData() {
        ArrayList<LearningData> infoList = new ArrayList<LearningData>();

        LearningData item1 = new LearningData();
        item1.setLearningName("人力资源管理(基础知识)");
        item1.setLearningInfo("236/620");
        item1.setLearningPro("9:00");
        item1.setLearningPic(R.drawable.movie);
        infoList.add(item1);

        LearningData item2 = new LearningData();
        item2.setLearningName("巡查员规程讲义");
        item2.setLearningInfo("63/97");
        item2.setLearningPro("7:50");
        item2.setLearningPic(R.drawable.movie);
        infoList.add(item2);

        LearningData item3 = new LearningData();
        item3.setLearningName("班组长培训第三集");
        item3.setLearningInfo("115/170");
        item3.setLearningPro("9-25 13:50");
        item3.setLearningPic(R.drawable.movie);
        infoList.add(item3);

        LearningData item4 = new LearningData();
        item4.setLearningName("企业文化");
        item4.setLearningInfo("215/370");
        item4.setLearningPro("9-20 13:50");
        item4.setLearningPic(R.drawable.movie);
        infoList.add(item4);

        LearningData item5 = new LearningData();
        item5.setLearningName("班组长培训第二集");
        item5.setLearningInfo("10/120");
        item5.setLearningPro("6-25 23:50");
        item5.setLearningPic(R.drawable.movie);
        infoList.add(item5);

        LearningData item6 = new LearningData();
        item6.setLearningName("班组长培训第三集");
        item6.setLearningInfo("10/120");
        item6.setLearningPro("6-25 23:50");
        item6.setLearningPic(R.drawable.movie);
        infoList.add(item6);

        LearningData item7 = new LearningData();
        item7.setLearningName("班组长培训第四集");
        item7.setLearningInfo("10/120");
        item7.setLearningPro("6-25 23:50");
        item7.setLearningPic(R.drawable.movie);
        infoList.add(item7);

        return infoList;
    }
}
