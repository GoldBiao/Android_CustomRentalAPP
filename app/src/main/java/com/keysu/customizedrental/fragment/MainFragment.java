package com.keysu.customizedrental.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.keysu.customizedrental.Config;
import com.keysu.customizedrental.R;
import com.keysu.customizedrental.activity.ConditionSelectActivity;
import com.keysu.customizedrental.activity.MapSearchHouseActivity;
import com.keysu.customizedrental.activity.SelectActivity;
import com.keysu.customizedrental.adapter.MainImageAdapter;
import com.keysu.customizedrental.entity.MainImageData;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.util.BannerUtils;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;


/**
 * 发现
 */
public class MainFragment extends LazyFragment implements View.OnClickListener {


    Banner banner;

    TextView tvCity;

    TextBannerView tvSelectBanner;

    List<String> mList = new ArrayList<>();


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_main);

        getData();
        getTvBanner();
        useBanner();
    }

    private void getTvBanner() {
        //设置数据

        mList.add("地铁三号线");
        mList.add("地铁四号线");
        mList.add("地铁六号线");
        mList.add("地铁十三号线");
        mList.add("地铁二十一号线");
        mList.add("地铁广佛线");
        mList.add("科学城");
        mList.add("大学城");
        mList.add("萝岗");
        mList.add("增城");
        mList.add("花都");
        mList.add("广州南站");
        mList.add("广州东站");
        mList.add("低涌");
        mList.add("大沙地");
        mList.add("猎德");
        mList.add("珠村");

        //调用setDatas(List<String>)方法后,TextBannerView自动开始轮播
        //注意：此方法目前只接受List<String>类型
        tvSelectBanner.setDatas(mList);


        tvSelectBanner.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {
                Intent i = new Intent(getActivity(), SelectActivity.class);
                i.putExtra("selectEtData",data);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

    /**
     * 获取数据
     */
    public void getData() {
        findViewById(R.id.tv_select).setOnClickListener(this);
        findViewById(R.id.rl_condition_select).setOnClickListener(this);
        findViewById(R.id.rl_map_select).setOnClickListener(this);
        tvCity = (TextView) findViewById(R.id.tv_city);
        tvCity.setOnClickListener(this);
        tvSelectBanner = (TextBannerView) findViewById(R.id.tv_select);
        banner = (Banner) findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_select:
                break;

            case R.id.rl_condition_select:
                startActivity(new Intent(getActivity(), ConditionSelectActivity.class));
                break;

            case R.id.rl_map_select:
                startActivity(new Intent(getActivity(), MapSearchHouseActivity.class));
                break;

            case R.id.tv_city:
                List<HotCity> hotCities = new ArrayList<>();
                hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
                hotCities.add(new HotCity("上海", "上海", "101020100"));
                hotCities.add(new HotCity("广州", "广东", "101280101"));
                hotCities.add(new HotCity("深圳", "广东", "101280601"));
                hotCities.add(new HotCity("杭州", "浙江", "101210101"));
                int mAnimStyle = com.zaaach.citypicker.R.style.DefaultCityPickerAnimation;
                CityPicker.from(MainFragment.this)
                        .enableAnimation(true)	//启用动画效果，默认无
                        .setAnimationStyle(mAnimStyle)	//自定义动画
                        .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101"))
                        .setHotCities(hotCities)	//指定热门城市
                    .setOnPickListener(new OnPickListener() {
                        @Override
                        public void onPick(int position, City data) {
                            tvCity.setText(data.getName());
                            Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel(){
                            Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLocate() {
                            //定位接口，需要APP自身实现，这里模拟一下定位
                        }
                    })
                    .show();
                break;
        }
    }

    public void useBanner() {
        List<MainImageData> list = new ArrayList<>();
        list.add(new MainImageData("https://img-blog.csdnimg.cn/20200524220705330.png", "这么好看", 1));
        list.add(new MainImageData("https://img-blog.csdnimg.cn/20200524220705336.png", "猴赛雷", 1));

        MainImageAdapter mainImageAdapter = new MainImageAdapter(list);

        banner.setAdapter(mainImageAdapter)
                .setCurrentItem(1,false)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setBannerRound(BannerUtils.dp2px(5))//圆角
                .addPageTransformer(new AlphaPageTransformer())//添加切换效果
                .setIndicator(new CircleIndicator(Config.CONTEXT))//设置指示器
        ;
    }

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();
        tvSelectBanner.startViewAnimator();
        banner.start();
    }

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        tvSelectBanner.stopViewAnimator();
        banner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner.destroy();
    }
}
