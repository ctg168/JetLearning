package com.jet2006.learning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;

import com.jet2006.terry.jetlearning.R;

public class MainActivity extends FragmentActivity {

    private FragmentManager frgManager;

    Button btnMy, btnQuestion, btnPaper, btnRes, btnExam, btnOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.frgManager = getSupportFragmentManager();

        this.btnMy = (Button) findViewById(R.id.btnMy);
        this.btnQuestion = (Button) findViewById(R.id.btnQuestion);
        this.btnPaper = (Button) findViewById(R.id.btnPaper);
        this.btnRes = (Button) findViewById(R.id.btnRes);
        this.btnExam = (Button) findViewById(R.id.btnExam);
        this.btnOffline = (Button) findViewById(R.id.btnOffline);

        this.frgManager
                .addOnBackStackChangedListener(new OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {

                        Log.i("fragment back stack", Integer
                                .toString(frgManager.getBackStackEntryCount()));

                        Fragment view = (Fragment) frgManager
                                .findFragmentById(R.id.views);
                        if (view instanceof MyFragment) {
                            displayNavgation(btnMy);
                        } else if (view instanceof QuestionFragment) {
                            displayNavgation(btnQuestion);
                        } else if (view instanceof PaperFragment) {
                            displayNavgation(btnPaper);
                        } else if (view instanceof QuestionListFragment) {
                            displayNavgation(btnRes);
                        } else if (view instanceof ExamFragment) {
                            displayNavgation(btnExam);
                        } else if (view instanceof OfflineFragment) {
                            displayNavgation(btnOffline);
                        }
                    }
                });

        onNavClick(this.btnMy);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i("MainOnkey", Integer.toString(event.getKeyCode()));

        Fragment view = (Fragment) frgManager.findFragmentById(R.id.views);
        if (view instanceof OnKeyListener) {
            if (((OnKeyListener) view).onKey(null, keyCode, event) == true) {
                return true;
            }
        }

        int count = frgManager.getBackStackEntryCount();
        if (count == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("您确定要离开吗？");
            builder.setNegativeButton("无情离开",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            android.os.Process.killProcess(android.os.Process
                                    .myPid());
                        }
                    });
            builder.setPositiveButton("继续学习",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onNavClick(View view) {
        displayNavgation(view);
        displayView(view.getTag().toString());
    }

    private void displayNavgation(View view) {
        this.btnMy.setSelected(false);
        this.btnQuestion.setSelected(false);
        this.btnPaper.setSelected(false);
        this.btnRes.setSelected(false);
        this.btnExam.setSelected(false);
        this.btnOffline.setSelected(false);
        view.setSelected(true);
    }

    @SuppressLint("InlinedApi")
    private void displayView(String tag) {
        Fragment view = (Fragment) frgManager.findFragmentById(R.id.views);

        if (tag.equals("0")) {
            view = new MyFragment();
        } else if (tag.equals("1")) {
            view = new QuestionFragment();
        } else if (tag.equals("2")) {
            view = new PaperFragment();
        } else if (tag.equals("3")) {
            view = new QuestionListFragment();
        } else if (tag.equals("4")) {
            view = new ExamFragment();
        } else if (tag.equals("5")) {
            view = new OfflineFragment();
        } else {
            return;
        }

        FragmentTransaction ft = frgManager.beginTransaction();

        ft.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // 可以返回之前的操作
        // ft.addToBackStack(null);
        ft.replace(R.id.views, view);

        ft.commit();
    }
}
