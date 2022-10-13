package com.keysu.customizedrental.activity;
//优惠券领取Act，已检查
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.utils.ToastUtil;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView coupon;
    Integer couponStatusCheck = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        //initData();
    }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        if (couponStatusCheck == 1) {
            ToastUtil.show("已经领取过了，下次再来吧！");
        } else {
            coupon.setImageDrawable(getResources().getDrawable(R.drawable.coupon_received));
            ToastUtil.show("领取成功");
            couponStatusCheck = 1;
        }
    }


    public void initData() {
        coupon = findViewById(R.id.coupon);
        coupon.setOnClickListener(this);
    }
}
