package com.jet2006.learning;

import java.util.ArrayList;
import java.util.Random;


import com.birin.gridlistviewadapters.Card;
import com.birin.gridlistviewadapters.ListGridAdapter;
import com.birin.gridlistviewadapters.dataholders.CardDataHolder;
import com.birin.gridlistviewadapters.utils.ChildViewsClickHandler;
import com.jet2006.terry.jetlearning.R;
import com.jet2006.entity.LearningData;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionListFragment extends Fragment {
    private ListView listView;
    private ArrayList<LearningData> dataList;
    private QuestionLearningAdapter listAdapter;

    private int MAX_CARDS = 2;
    private final int QUESTION_COUNT = 100;
    int[] ColorList = new int[]{R.color.card_color_1, R.color.card_color_2,
            R.color.card_color_3, R.color.card_color_4, R.color.card_color_5,
            R.color.card_color_6,};

    String[] BookNameList = new String[]{"家居防火与逃生", "发电机工作原理",
            "发电机检修内容发电机检修内容发电机检修内容发电机检修内容", "游标卡尺的结构", "低厂变色谱分析", "吹灰器检修培训",
            "发电机检修内容发电机检修内容发电机检修内容发", "岗位操作票考试", "摇臂和圆盘的检修", "安全生产禁令学习",
            "发电机检修内容发电机检修内容发电机检修内容发", "消防安全知识培训", "电厂阀门如何分类", "双臂电桥使用方法",
            "直流系统相关知识", "核电检修知识学习", "核电安全知识学习", "钳工理论知识培训", "封闭母线耐压试验",
            "消防安全知识学习", "设备亮化治理研讨", "电刷的选用及维护", "技术问答（采样）", "如何防止皮带打滑",
            "调车作业注意事项", "管理人员岗位培训", "新大学生上岗考试", "外径千分尺的读法", "变压器检修与试验",
            "缺陷管理制度学习", "特殊工种岗位培训", "防误操作规定考试", "低压电机检修流程", "低压电机检修工艺",
            "变压器油色谱分析", "直流电机检修工艺"};

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_data_list, container, false);
        generateSomeDummyDataList();

        listView = (ListView) view.findViewById(R.id.lv);// new
        listView.setDivider(null);

        MAX_CARDS = this.getResources().getInteger(R.integer.cards_one_row);
        listAdapter = new QuestionLearningAdapter(getActivity().getApplicationContext(), MAX_CARDS);
        listAdapter.addItemsInGrid(dataList);
        listView.setAdapter(listAdapter);

//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),ResourceDetailActivity.class));
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),ResourceDetailActivity.class));
            }
        });

        return view;
    }

    private void generateSomeDummyDataList() {
        dataList = new ArrayList<LearningData>();
        for (int i = 0; i < QUESTION_COUNT; i++) {
            String BookName = BookNameList[new Random()
                    .nextInt(BookNameList.length)];
            dataList.add(new LearningData(i, BookName, "DESC", "DESC", R.drawable.check_normal));
        }
    }
}

class QuestionLearningViewHolder {
    TextView nameView;
    TextView descView;
    View containerView;
}

class QuestionLearningAdapter extends ListGridAdapter<LearningData, QuestionLearningViewHolder> {
    private Context mContext;

    public QuestionLearningAdapter(Context context, int totalCardsInRow) {
        super(context, totalCardsInRow);
    }

    @Override
    protected Card<QuestionLearningViewHolder> getNewCard(int cardwidth) {
        // Create card through XML(can be created programmatically as well.)
        View cardView = getLayoutInflater()
                .inflate(R.layout.learning_card_layout, null);
            cardView.setMinimumHeight(cardwidth / 2);

        // Now create card view holder.
        QuestionLearningViewHolder viewHolder = new QuestionLearningViewHolder();
        viewHolder.nameView = (TextView) cardView.findViewById(R.id.bookName);
        viewHolder.descView = (TextView) cardView.findViewById(R.id.bookDesc);
        viewHolder.containerView = cardView.findViewById(R.id.container);

        return new Card<QuestionLearningViewHolder>(cardView, viewHolder);
    }

    @Override
    protected void setCardView(CardDataHolder<LearningData> cardDataHolder, QuestionLearningViewHolder cardViewHolder) {
        LearningData item = cardDataHolder.getData();
        cardViewHolder.nameView.setText(item.getLearningName());
        cardViewHolder.descView.setText(item.getLearningInfo());

        int pos = item.getId();
        int pColor = R.color.card_color_4;
        if (pos < 20) {
            pColor = R.color.card_color_1;
        } else if (pos < 40) {
            pColor = R.color.card_color_2;
        } else if (pos < 60) {
            pColor = R.color.card_color_3;
        } else if (pos < 80) {
            pColor = R.color.card_color_5;
        } else {
            pColor = R.color.card_color_6;
        }
        cardViewHolder.containerView.setBackgroundColor(getContext()
                .getResources().getColor(pColor));
    }

    @Override
    protected void onCardClicked(LearningData cardData) {
        //Toast.makeText(getContext(), "Card click " + cardData.getLearningName(), Toast.LENGTH_SHORT).show();
        //this.getContext().startActivity(new Intent(this.getContext(),ResourceDetailActivity.class));


        gotoDetailPage(cardData);
    }

    private final int TEXT_VIEW_CLICK_ID = 0;

    @Override
    protected void registerChildrenViewClickEvents(QuestionLearningViewHolder cardViewHolder,
                                                   ChildViewsClickHandler childViewsClickHandler) {
        childViewsClickHandler.registerChildViewForClickEvent(cardViewHolder.nameView, TEXT_VIEW_CLICK_ID);
    }

    @Override
    protected void onChildViewClicked(View clickedChildView, LearningData cardData, int eventId) {
        if (eventId == TEXT_VIEW_CLICK_ID) {
            gotoDetailPage(cardData);
//            Toast.makeText(
//                    getContext(),
//                    "TextView click " + cardData.getLearningName(),
//                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void setRowView(View rowView, int position) {
        rowView.setBackgroundColor(Color.WHITE);
    }

    private void gotoDetailPage(LearningData cardData)
    {
        Intent intent = new Intent(this.getContext(),ResourceDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Bundle data = new Bundle();
        data.putString("LearningName",cardData.getLearningName());
        intent.putExtras(data);
        this.getContext().startActivity(intent);
    }

}
