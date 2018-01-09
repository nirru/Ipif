package com.oxilo.ipif;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.activity.SendGiftActivity;
import com.oxilo.ipif.util.DeviceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.l1)
    LinearLayout l1;
    @BindView(R.id.l2)
    LinearLayout l2;
    @BindView(R.id.l3)
    LinearLayout l3;
    @BindView(R.id.top_layout)
    RelativeLayout top_layout;
    @BindView(R.id.bottom_layout)
    RelativeLayout bottom_layout;
    @BindView(R.id.redeem_gift_btn)
    ImageButton redeem_gift_btn;

    @BindView(R.id.img_send_gift)
    ImageView img_send_gift;
    @BindView(R.id.img_account)
    ImageView img_account;
    @BindView(R.id.img_redeem_gift_img)
    ImageView img_redeem_gift_img;

    @BindView(R.id.txt_send_gift)
    TextView txt_send_gift;
    @BindView(R.id.txt_account)
    TextView txt_account;
    @BindView(R.id.txt_gift)
    TextView txt_gift;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        splitScreen();

        top_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent i1 = new Intent(MainActivity.this, SendGiftActivity.class);
                    i1.putExtra("A", "Send Gift");
                    startActivity(i1);
                    top_layout.setBackground(getResources().getDrawable(R.color.white));
                    img_send_gift.setImageResource(R.drawable.send_gift);
                    txt_send_gift.setTextColor(getResources().getColor(R.color.red));
                    // Do what you want
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    top_layout.setBackground(getResources().getDrawable(R.color.red));
                    img_send_gift.setImageResource(R.drawable.send_gift_white);
                    txt_send_gift.setTextColor(getResources().getColor(R.color.white));
                    // Do what you want
                    return true;
                }
                return false;
            }
        });
        bottom_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent i1 = new Intent(MainActivity.this, MyAccountActivity.class);
                    i1.putExtra("A", "Account");
                    startActivity(i1);
                    bottom_layout.setBackground(getResources().getDrawable(R.color.white));
                    img_account.setImageResource(R.drawable.account);
                    txt_account.setTextColor(getResources().getColor(R.color.red));
                    // Do what you want
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    bottom_layout.setBackground(getResources().getDrawable(R.color.red));
                    img_account.setImageResource(R.drawable.account_white);
                    txt_account.setTextColor(getResources().getColor(R.color.white));
                    // Do what you want
                    return true;
                }
                return false;
            }
        });
        img_redeem_gift_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent i1 = new Intent(MainActivity.this, SampleActivity.class);
                    i1.putExtra("A", "Redeem Gift");
                    startActivity(i1);
                    redeem_gift_btn.setBackground(getResources().getDrawable(R.drawable.ic_triangle_right));
                    img_redeem_gift_img.setImageResource(R.drawable.redeem_gift);
                    txt_gift.setTextColor(getResources().getColor(R.color.white));
                    // Do what you want
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    redeem_gift_btn.setBackground(getResources().getDrawable(R.drawable.ic_triangle_white));
                    img_redeem_gift_img.setImageResource(R.drawable.redeem_gift_red);
                    txt_gift.setTextColor(getResources().getColor(R.color.red));
                    // Do what you want
                    return true;
                }
                return false;
            }
        });

    }

    private void splitScreen() {
        int height = new DeviceUtils(MainActivity.this).getHeight() - getResources().getDimensionPixelSize(R.dimen.toolbar_height);

//        acBalance.getLayoutParams().width = height/3;
//        acBalance.getLayoutParams().height = height/3;
//
//        claimDrink.getLayoutParams().width = height/3;
//        claimDrink.getLayoutParams().height = height/3;
//
//        sendDrink.getLayoutParams().width = height/3;
//        sendDrink.getLayoutParams().height = height/3;


//        acBalance.setLayoutParams(new LinearLayout.LayoutParams((int) height / 3,
//                (int) height /3 ));
//        claimDrink.setLayoutParams(new LinearLayout.LayoutParams((int) height / 3,
//                (int) height /3 ));
//        sendDrink.setLayoutParams(new LinearLayout.LayoutParams((int) height / 3,
//                (int) height /3 ));
    }


//    @OnClick({R.id.top_layout, R.id.bottom_layout, R.id.redeem_gift_btn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.top_layout:
//               Intent i2 = new Intent(MainActivity.this,SampleActivity.class);
//                i2.putExtra("A","Send Gift");
//                startActivity(i2);
//                break;
//            case R.id.bottom_layout:
//                Intent i = new Intent(MainActivity.this,SampleActivity.class);
//                i.putExtra("A","Account");
//                startActivity(i);
//                break;
//            case R.id.redeem_gift_btn:
//               Intent i1 = new Intent(MainActivity.this,SampleActivity.class);
//                i1.putExtra("A","Redeem Coupon");
//                startActivity(i1);
//                break;
//        }
//    }


}
