package com.oxilo.ipif;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;

public class SampleActivity extends BaseDrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        TextView title = (TextView)findViewById(R.id.title);

        if (getIntent()!=null){
            title.setText(getIntent().getStringExtra("A"));
        }
    }
}
