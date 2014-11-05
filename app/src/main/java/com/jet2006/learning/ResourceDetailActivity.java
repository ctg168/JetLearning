package com.jet2006.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.jet2006.terry.jetlearning.R;

public class ResourceDetailActivity extends Activity {
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_detail_page);
        title = (TextView) findViewById(R.id.resource_title);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String username = data.getString("LearningName");
        title.setText(username);
    }
}
