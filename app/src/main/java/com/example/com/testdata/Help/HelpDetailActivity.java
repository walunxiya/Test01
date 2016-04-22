package com.example.com.testdata.Help;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.com.testdata.R;
import com.example.com.testdata.bean.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelpDetailActivity extends AppCompatActivity {

    private ListView listView;
    private List<Comment> list = new ArrayList<>();
    private HelpAdapter adapter;
    private Button btn_comment;
    private EditText edit_comment;
    private int[] image = {R.mipmap.dogdemo};
    private Comment comment = new Comment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        init();
        listener();
    }



    private void init() {

        listView = (ListView)findViewById(R.id.lv_help);
        adapter = new HelpAdapter(this,list);
        edit_comment = (EditText)findViewById(R.id.edit_comment);
        btn_comment = (Button)findViewById(R.id.btn_comment);
    }

    private void listener() {
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setImage(image[0]);
                comment.setName("徐往：");
                comment.setContent(edit_comment.getText().toString());
                comment.setDate(new Date());
                list.add(comment);
//                adapter.notifyDataSetChanged();
                adapter.addComment(list);
            }
        });

    }
}
