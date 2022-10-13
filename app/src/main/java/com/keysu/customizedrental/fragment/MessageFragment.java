package com.keysu.customizedrental.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.activity.ItemDetailActivity;


/**
 * 消息
 */
public class MessageFragment extends LazyFragment implements View.OnClickListener {



    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.friend_list_item);
        initData();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

    /**
     * 获取数据
     */
    public void initData() {

        findViewById(R.id.house_master1).setOnClickListener(this);
        findViewById(R.id.house_master2).setOnClickListener(this);
        findViewById(R.id.house_master3).setOnClickListener(this);
        findViewById(R.id.house_master4).setOnClickListener(this);
        findViewById(R.id.house_master5).setOnClickListener(this);
        findViewById(R.id.house_master6).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.house_master1:
                intent.putExtra("who", "房东1");
                intent.putExtra("whose", 1);
                break;
            case R.id.house_master2:
                intent.putExtra("who", "房东2");
                intent.putExtra("whose", 2);
                break;
            case R.id.house_master3:
                intent.putExtra("who", "房东3");
                intent.putExtra("whose", 3);
                break;
            case R.id.house_master4:
                intent.putExtra("who", "房东4");
                intent.putExtra("whose", 4);
                break;
            case R.id.house_master5:
                intent.putExtra("who", "房东5");
                intent.putExtra("whose", 5);
                break;
            case R.id.house_master6:
                intent.putExtra("who", "房东6");
                intent.putExtra("whose", 6);
                break;
        }
        intent.setClass(getActivity(), ItemDetailActivity.class);
        startActivity(intent);
    }
}
