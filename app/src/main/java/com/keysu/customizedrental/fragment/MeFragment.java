package com.keysu.customizedrental.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.activity.CardActivity;
import com.keysu.customizedrental.utils.ToastUtil;


/**
 * 我
 */
public class MeFragment extends LazyFragment implements View.OnClickListener {



    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_me);
        getData();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

    /**
     * 获取数据
     */
    public void  getData() {
        findViewById(R.id.txt_album).setOnClickListener(this);
        findViewById(R.id.txt_card).setOnClickListener(this);
        findViewById(R.id.txt_collect).setOnClickListener(this);
        findViewById(R.id.txt_money).setOnClickListener(this);
        findViewById(R.id.txt_smail).setOnClickListener(this);
        findViewById(R.id.txt_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.txt_album:
                // 调用系统相册
                intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
                break;
            case R.id.txt_card:
                intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_collect:
                ToastUtil.show("收藏");
                break;
            case R.id.txt_money:
                ToastUtil.show("钱包");
                break;
            case R.id.txt_smail:
                ToastUtil.show("表情");
                break;
            case R.id.txt_setting:
                ToastUtil.show("设置");
                break;
        }
    }
}
