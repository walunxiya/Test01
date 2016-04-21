package com.example.com.testdata.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.testdata.R;

public class JoinActivityActivity extends AppCompatActivity {

    private TextView titleName;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        
        init();
    }

    private void init() {

        titleName = (TextView)findViewById(R.id.tv_titlename);
        titleName.setText("活动参加");
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JoinActivityActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                JoinActivityActivity.this.finish();
            }
        });
    }
}
