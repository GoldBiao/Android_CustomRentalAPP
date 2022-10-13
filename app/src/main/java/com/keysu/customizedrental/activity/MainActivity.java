package com.keysu.customizedrental.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.adapter.MyFragmentPagerAdapter;
import com.keysu.customizedrental.fragment.MainFragment;
import com.keysu.customizedrental.fragment.MeFragment;
import com.keysu.customizedrental.fragment.MessageFragment;
import com.keysu.customizedrental.view.TabGroupView;
import com.keysu.customizedrental.view.TabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TabGroupView.OnItemClickListener{
    
    private static final String TAG = "MainActivity";

    private List<Fragment> mFragments = new ArrayList<>();

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.group_tab_layout)
    TabGroupView mTabGroupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragments();
        initView();
    }

    private void initView() {

        mTabGroupView.setOnItemClickListener(this);
        mTabGroupView.setHasNew(1,true);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabGroupView.onScrolling(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mTabGroupView.setCurrentItem(position);
                mTabGroupView.setHasNew(position,false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragments() {
        mFragments.add(new MainFragment());
        mFragments.add(new MessageFragment());
        mFragments.add(new MeFragment());
    }

    @Override
    public void onClick(TabView tabLayout, int position) {
        mViewPager.setCurrentItem(position, false);
        mTabGroupView.setHasNew(position,false);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: ");
    }
}
