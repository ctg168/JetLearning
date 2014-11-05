package com.jet2006.learning;

import java.util.ArrayList;
import java.util.HashMap;



import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.CornerListView;
import com.jet2006.entity.ExamData;
import com.jet2006.entity.LearningData;

public class MyLearningFragment extends Fragment {

    private CornerListView examTaskListView = null;
    private CornerListView learningInfoListView = null;
    // private SimpleAdapter adapter1 = null;
    // private SimpleAdapter adapter2 = null;
    private AlertDialog.Builder msg = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_learning, container,
                false);

        this.examTaskListView = (CornerListView) view
                .findViewById(R.id.myExamTaskList);

        this.learningInfoListView = (CornerListView) view
                .findViewById(R.id.myLearingInfo);

        this.msg = new AlertDialog.Builder(getActivity());

        // this.adapter1 = new SimpleAdapter(view.getContext(), this.getItem1(),
        // R.layout.my_learning_exam_task_list_item, new String[] {
        // "examname", "examtimer", "examlenght", "examstatus" },
        // new int[] { R.id.examName, R.id.examTimer, R.id.examlength,
        // R.id.examStatus });
        //
        // this.adapter2 = new SimpleAdapter(view.getContext(), this.getItem2(),
        // R.layout.my_learning_info_list_item, new String[] {
        // "learningname", "learninginfo", "learningpro" },
        // new int[] { R.id.learningname, R.id.learninginfo,
        // R.id.learningpro });

        // this.cornerListView.setAdapter(this.adapter1);
        // this.learningInfoListView.setAdapter(this.adapter2);

        this.examTaskListView.setItemLayout(
                R.layout.my_learning_exam_task_list_item, ExamData.filed());
        this.learningInfoListView.setItemLayout(
                R.layout.my_learning_info_list_item, LearningData.field());

        this.examTaskListView.setDataSource(getItem1());
        this.learningInfoListView.setDataSource(getItem2());

        this.examTaskListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ListView listView = (ListView) parent;

                HashMap<String, String> map = (HashMap<String, String>) listView
                        .getItemAtPosition(position);
                msg.setMessage("name:" + map.get("ExamName"));
                msg.show();
            }
        });

        this.learningInfoListView
                .setOnItemClickListener(new OnItemClickListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        ListView listView = (ListView) parent;
                        HashMap<String, String> map = (HashMap<String, String>) listView
                                .getItemAtPosition(position);
                        msg.setMessage("name:" + map.get("LearningName"));
                        msg.show();
                    }
                });

        return view;
    }

    public ArrayList<ExamData> getItem1() {
        ArrayList<ExamData> examList = new ArrayList<ExamData>();

        ExamData item1 = new ExamData();
        item1.setExamName("人力资源管理考试");
        item1.setExamTimer("8-30 9:00~10:00");
        item1.setExamLenght("时长:60分钟");
        item1.setExamStatus("已开考");
        examList.add(item1);

        ExamData item2 = new ExamData();
        item2.setExamName("短信答题");
        item2.setExamTimer("9-05 9:00~9:05");
        item2.setExamLenght("时长:5分钟");
        item2.setExamStatus("已开考");
        examList.add(item2);

        ExamData item3 = new ExamData();
        item3.setExamName("安全安规考试");
        item3.setExamTimer("9-15 14:00~16:00");
        item3.setExamLenght("时长:120分钟");
        item3.setExamStatus("备考中");
        examList.add(item3);

        ExamData item4 = new ExamData();
        item4.setExamName("安全安规考试二");
        item4.setExamTimer("9-15 14:00~16:00");
        item4.setExamLenght("时长:120分钟");
        item4.setExamStatus("备考中");
        examList.add(item4);

        ExamData item5 = new ExamData();
        item5.setExamName("安全安规考试三");
        item5.setExamTimer("9-15 14:00~16:00");
        item5.setExamLenght("时长:120分钟");
        item5.setExamStatus("备考中");
        examList.add(item5);

//		ExamData item6 = new ExamData();
//		item6.setExamName("安全安规考试三");
//		item6.setExamTimer("9-15 14:00~16:00");
//		item6.setExamLenght("时长:120分钟");
//		item6.setExamStatus("备考中");
//		examList.add(item6);
//
//		ExamData item7 = new ExamData();
//		item7.setExamName("安全安规考试三");
//		item7.setExamTimer("9-15 14:00~16:00");
//		item7.setExamLenght("时长:120分钟");
//		item7.setExamStatus("备考中");
//		examList.add(item7);
//
//		ExamData item8 = new ExamData();
//		item8.setExamName("安全安规考试三");
//		item8.setExamTimer("9-15 14:00~16:00");
//		item8.setExamLenght("时长:120分钟");
//		item8.setExamStatus("备考中");
//		examList.add(item8);
//
//		ExamData item9 = new ExamData();
//		item9.setExamName("安全安规考试三");
//		item9.setExamTimer("9-15 14:00~16:00");
//		item9.setExamLenght("时长:120分钟");
//		item9.setExamStatus("备考中");
//		examList.add(item9);

        return examList;
    }

    public ArrayList<LearningData> getItem2() {
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
        item2.setLearningPic(R.drawable.mp3);
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
        item5.setLearningPic(R.drawable.powerpoint);
        infoList.add(item5);

//		LearningData item6 = new LearningData();
//		item6.setLearningName("班组长培训第三集");
//		item6.setLearningInfo("10/120");
//		item6.setLearningPro("6-25 23:50");
//		item6.setLearningPic(R.drawable.powerpoint);
//		infoList.add(item6);
//
//		LearningData item7 = new LearningData();
//		item7.setLearningName("班组长培训第四集");
//		item7.setLearningInfo("10/120");
//		item7.setLearningPro("6-25 23:50");
//		item7.setLearningPic(R.drawable.powerpoint);
//		infoList.add(item7);

        return infoList;
    }
}

/*
 *
 * private ImageView trash = null;
 *
 * private Button selallButton, clearButton, delButton, cancelButton;
 *
 *
 *
 * this.trash = (ImageView) view.findViewById(R.id.trash);
 *
 * View popView = inflater .inflate(R.layout.pop_deltools, container, false);
 *
 * final PopupWindow pop = new PopupWindow(popView, LayoutParams.FILL_PARENT,
 * 90, false);
 *
 * this.selallButton = (Button) popView.findViewById(R.id.btnSelAll);
 * this.clearButton = (Button) popView.findViewById(R.id.btnClear);
 * this.delButton = (Button) popView.findViewById(R.id.btnDel);
 * this.cancelButton = (Button) popView.findViewById(R.id.btnCancel);
 *
 *
 * this.trash.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { if (!pop.isShowing()) {
 * pop.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
 *
 * setListViewCheckVisibility(true); } else { pop.dismiss();
 *
 * setListViewCheckVisibility(false); } } });
 *
 * selallButton.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { setListViewCheck(true);
 * selallButton.setVisibility(View.GONE);
 * clearButton.setVisibility(View.VISIBLE); } });
 *
 * clearButton.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { setListViewCheck(false);
 *
 * selallButton.setVisibility(View.VISIBLE);
 * clearButton.setVisibility(View.GONE); } });
 *
 * delButton.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { msg.setMessage(getListViewCheck());
 * msg.show(); } });
 *
 * cancelButton.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) {
 *
 * setListViewCheckVisibility(false); setListViewCheck(false);
 *
 * selallButton.setVisibility(View.VISIBLE);
 * clearButton.setVisibility(View.GONE); pop.dismiss(); } });
 *
 *
 * private void setListViewCheckVisibility(Boolean isShow) { for (int i = 0; i <
 * this.learningInfoListView.getChildCount(); i++) { View view =
 * this.learningInfoListView.getChildAt(i); CheckBox box = (CheckBox)
 * view.findViewById(R.id.delcheck); box.setVisibility(isShow ? View.VISIBLE :
 * View.GONE); } }
 *
 * private void setListViewCheck(Boolean isCheck) { for (int i = 0; i <
 * this.learningInfoListView.getChildCount(); i++) { View view =
 * this.learningInfoListView.getChildAt(i); CheckBox box = (CheckBox)
 * view.findViewById(R.id.delcheck); box.setChecked(isCheck); } }
 *
 * @SuppressWarnings("unchecked") private String getListViewCheck() { String
 * nameString = ""; for (int i = 0; i <
 * this.learningInfoListView.getChildCount(); i++) { View view =
 * this.learningInfoListView.getChildAt(i); CheckBox box = (CheckBox)
 * view.findViewById(R.id.delcheck); if (box.isChecked()) { HashMap<String,
 * Object> obj = (HashMap<String, Object>) this.adapter2 .getItem(i);
 *
 * nameString += obj.get("learningname").toString() + ','; } }
 *
 * return nameString; }
 */

