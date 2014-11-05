package com.jet2006.learning;


import com.jet2006.terry.jetlearning.R;
import com.jet2006.controls.PagerSlidingTabStrip;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    private ImageView mySetting;

    private Fragment taskFragment, learningFragment, fovFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);

        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        pager = (ViewPager) view.findViewById(R.id.pager);
        mySetting = (ImageView) view.findViewById(R.id.usersetting);

        mySetting.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
            }
        });

        // fragment嵌套使用时，使用getChildFragmentManager,
        adapter = new MyPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);

        return view;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "我的学习", "学分任务", "收藏夹" };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    if (learningFragment == null) {
                        learningFragment = new MyLearningFragment();
                    }
                    return learningFragment;
                case 1:
                    if (taskFragment == null) {
                        taskFragment = new MyTaskFragment();
                    }
                    return taskFragment;
                case 2:
                    if (fovFragment == null) {
                        fovFragment = new MyFovFragment();
                    }
                    return fovFragment;
                default:
                    return null;
            }
        }
    }
}
