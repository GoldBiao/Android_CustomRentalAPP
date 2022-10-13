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

public class HouseAcreageItemView extends PartShadowPopupView{


    private String[] acreage = { "50m²以下","50-80m²","80-100m²","100m²以上"};


    ListView acreageListView;

    private String houseAcreage;

    public void setHouseAcreage(String houseAcreage) {
        this.houseAcreage = houseAcreage;
    }

    public String getHouseAcreage() {
        return houseAcreage;
    }

    public HouseAcreageItemView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_acreage_select_item_view;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        initView();

    }

    private void initView() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, acreage);
        acreageListView = findViewById(R.id.acreage_listview);
        acreageListView.setAdapter(arrayAdapter);


        acreageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("点击了"+acreage[position]);
                setHouseAcreage(acreage[position]);
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
