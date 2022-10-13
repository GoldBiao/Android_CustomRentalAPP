package com.keysu.customizedrental.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.utils.ToastUtil;
import com.lxj.xpopup.impl.PartShadowPopupView;

public class ConditionSelectItemView extends PartShadowPopupView{


    private String[] area = { "越秀区","荔湾区","海珠区","天河区",
            "白云区","黄埔区","番禺区",
            "花都区","南沙区","增城区","从化区"
    };


    ListView cityListView;

    private String cityArea;

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getCityArea() {
        return cityArea;
    }

    public ConditionSelectItemView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_condition_select_item_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        initView();

    }

    private void initView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, area);
        cityListView = findViewById(R.id.city_listview);
        cityListView.setAdapter(arrayAdapter);


        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("点击了"+area[position]);
                setCityArea(area[position]);
                dismiss();
            }
        });
    }

    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag","CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag","CustomPartShadowPopupView onDismiss");
    }


}
