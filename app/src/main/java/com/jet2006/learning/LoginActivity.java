package com.jet2006.learning;

import android.widget.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.jet2006.terry.jetlearning.R;

public class LoginActivity extends FragmentActivity {
    private CheckBox chkSaveInfo;

    public void onLogin(View view) {
        String account = ((EditText) findViewById(R.id.txtAccount)).getText().toString();
        String password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();

        chkSaveInfo = (CheckBox) findViewById(R.id.chkSaveInfo);

        if (!account.equals("guigui") && !password.equals("888888")) {
            if(chkSaveInfo.isChecked()){
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this.getApplicationContext(), "用户名或密码错误!", Toast.LENGTH_SHORT).show();
        }
    }


//        startActivity(intent);
//
//        LoginActivity.this.finish();
//
//
//        AlertDialog.Builder msg = new AlertDialog.Builder(this);
//
//        if (!account.equals("guigui") && !password.equals("888888")) {
//
//            JetApplication user = (JetApplication)getApplication();
//            user.userName = "guigui";
//            user.userTag = "888888";
//
//            Intent intent = new Intent();
//
//            intent.setClass(LoginActivity.this, MainActivity.class);
//
//            startActivity(intent);
//
//            LoginActivity.this.finish();
//
//        } else {
//            msg.setMessage("账号或密码错误，请核对后重新输入！");
//        }
//
//        msg.create().show();
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
