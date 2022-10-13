package com.keysu.customizedrental.activity;
//条件找房界面
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.adapter.HouseListAdapter;
import com.keysu.customizedrental.entity.HouseList;
import com.keysu.customizedrental.utils.ToastUtil;
import com.keysu.customizedrental.view.ConditionSelectItemView;
import com.keysu.customizedrental.view.HouseAcreageItemView;
import com.keysu.customizedrental.view.OnItemClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.BottomMenu;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;

import java.util.ArrayList;
import java.util.List;


public class ConditionSelectActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ConditionSelectActivity";

    TextView tvCityArea;

    TextView tvHouseAcreage;

    ImageView ivBack;

    Toolbar toolbar;

    private ConditionSelectItemView conditionSelectItemView;

    private HouseAcreageItemView houseAcreageItemView;

    private List<HouseList> houseLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_select);
        initData();
        initView();
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                startActivity(new Intent(ConditionSelectActivity.this,MapSearchHouseActivity.class));
                finish();
                break;
            case R.id.home_page:
                finish();
                break;
        }
        return true;
    }

    private void initData() {
        HouseList h1 = new HouseList(R.mipmap.h1,"白云区泰村镇","55m²",
                "卡顿大酒店，陈田花园","2年",1600,false);
        houseLists.add(h1);
        HouseList h2 = new HouseList(R.mipmap.h2,"白云区云翔路","75m²",
                "白云山风景区，南方医院","1.5年",2000,true);
        houseLists.add(h2);
        HouseList h3 = new HouseList(R.mipmap.h3,"白云区沙太北路","90m²",
                "广州誉德莱国际学校，天健家具装饰广场","1年",2600,true);
        houseLists.add(h3);
        HouseList h4 = new HouseList(R.mipmap.h4,"白云区同和路","120m²",
                "中大附属外国语小学，南湖游乐园","3年",4000,true);
        houseLists.add(h4);
        HouseList h5 = new HouseList(R.mipmap.h5,"海珠区西碌北大街","88m²",
                "广州协佳医院","2年",2100,true);
        houseLists.add(h5);
        HouseList h6 = new HouseList(R.mipmap.h1,"白云区泰村镇","55m²",
                "卡顿大酒店，陈田花园","2年",1600,false);
        houseLists.add(h6);
        HouseList h7 = new HouseList(R.mipmap.h2,"白云区云翔路","75m²",
                "白云山风景区，南方医院","1.5年",2000,true);
        houseLists.add(h7);
        HouseList h8 = new HouseList(R.mipmap.h3,"白云区沙太北路","90m²",
                "广州誉德莱国际学校，天健家具装饰广场","1年",2600,true);
        houseLists.add(h8);
        HouseList h9 = new HouseList(R.mipmap.h4,"白云区同和路","120m²",
                "中大附属外国语小学，南湖游乐园","3年",4000,true);
        houseLists.add(h9);
        HouseList h10 = new HouseList(R.mipmap.h5,"海珠区西碌北大街","88m²",
                "广州协佳医院","2年",2100,true);
        houseLists.add(h10);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbartitle);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
        tvCityArea = findViewById(R.id.tv_area);
        tvHouseAcreage = findViewById(R.id.tv_acreage);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;
        findViewById(R.id.rl_area).setOnClickListener(this);
        findViewById(R.id.rl_acreage).setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.house_list_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HouseListAdapter adapter = new HouseListAdapter(houseLists);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
                                           @Override
                                           public void onItemClick(View view, int postion) {
                                               ToastUtil.show("点击了第ace" + postion);
                                               BottomMenu.show(ConditionSelectActivity.this, new String[]{"公交路线", "驾车路线", "步行路线", "骑行路线"},
                                                       new OnMenuItemClickListener() {
                                                   @Override
                                                   public void onClick(String text, int index) {


                                                        switch (index) {
                                                            case 0:
                                                                // 公交路线规划
                                                                Intent i1 = new Intent();
                                                                i1.setData(Uri.parse("baidumap://map/direction?" +
                                                                        "destination=广州市白云区泰村镇" +
                                                                        "&coord_type=bd09ll" +
                                                                        "&mode=transit" +
                                                                        "&sy=0" +
                                                                        "&index=0" +
                                                                        "&target=1" +
                                                                        "&src=andr.baidu.openAPIdemo"));
                                                                startActivity(i1);
                                                                break;
                                                            case 1:
                                                                // 驾车导航
                                                                Intent i2 = new Intent();
                                                                i2.setData(Uri.parse("baidumap://map/direction?" +
                                                                        "destination=文光塔" +
                                                                        "&coord_type=bd09ll" +
                                                                        "&mode=driving" +
                                                                        "&src=andr.baidu.openAPIdemo"));
                                                                startActivity(i2);
                                                                break;
                                                            case 2:
                                                                // 步行路线规划
                                                                Intent i3 = new Intent();
                                                                i3.setData(Uri.parse("baidumap://map/direction?" +
                                                                        "destination=文光塔" +
                                                                        "&coord_type=bd09ll" +
                                                                        "&mode=walking" +
                                                                        "&src=andr.baidu.openAPIdemo"));
                                                                startActivity(i3);
                                                                break;
                                                            case 3:
                                                                // 骑行路线规划
                                                                Intent i4 = new Intent();
                                                                i4.setData(Uri.parse("baidumap://map/direction?" +
                                                                        "destination=文光塔" +
                                                                        "&coord_type=bd09ll" +
                                                                        "&mode=riding" +
                                                                        "&src=andr.baidu.openAPIdemo"));
                                                                startActivity(i4);
                                                                break;
                                                        }


                                                   }
                                               }).setCustomView(R.layout.layout_baidu_map_buttom_menu, new BottomMenu.OnBindView() {
                                                   @Override
                                                   public void onBind(BottomMenu bottomMenu, View v) {
                                                       //绑定布局事件
                                                       v.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                           }
                                                       });
                                                   }
                                               });
                                           }
                                       });


            }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_area:
                showCityAreaItem(v);
                break;

            case R.id.rl_acreage:
                showAcreageItem(v);
                break;

            case R.id.iv_back:
                finish();
                break;
        }
    }

    //房屋面积选择
    private void showAcreageItem(final View v) {

        if(houseAcreageItemView ==null){
            houseAcreageItemView = (HouseAcreageItemView) new XPopup.Builder(ConditionSelectActivity.this)
                    .atView(v)
                    .maxHeight(600)
                    .setPopupCallback(new SimpleCallback() {
                        @Override
                        public void onShow() {
                            ToastUtil.show("显示了房屋面积");
                        }
                        @Override
                        public void onDismiss() {
                            tvHouseAcreage.setText(houseAcreageItemView.getHouseAcreage());

                        }
                    })
                    .asCustom(new HouseAcreageItemView(ConditionSelectActivity.this));
        }

        houseAcreageItemView.show();

    }

    //区域选择
    public void showCityAreaItem(final View v) {
        if(conditionSelectItemView ==null){
        conditionSelectItemView = (ConditionSelectItemView) new XPopup.Builder(ConditionSelectActivity.this)
                .atView(v)
                .maxHeight(600)
                .setPopupCallback(new SimpleCallback() {
                    @Override
                    public void onShow() {
                            ToastUtil.show("显示了区域");
                    }
                    @Override
                    public void onDismiss() {
                        tvCityArea.setText(conditionSelectItemView.getCityArea());
                    }
                })
                .asCustom(new ConditionSelectItemView(ConditionSelectActivity.this));
    }

        conditionSelectItemView.show();
    }

}

