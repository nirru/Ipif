package com.oxilo.ipif;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.oxilo.ipif.modal.login.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerificationActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.number)
    TextView number;
    private UserData userData;
    private String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            userData = getIntent().getParcelableExtra("USER");
            no = getIntent().getStringExtra("N");
            number.setText(no);
        }


    }

    @OnClick(R.id.verification)
    public void onViewClicked() {
        Intent i = new Intent(VerificationActivity.this, ProfileActivity.class);
        i.putExtra("USER", userData);
        startActivity(i);
    }
}
