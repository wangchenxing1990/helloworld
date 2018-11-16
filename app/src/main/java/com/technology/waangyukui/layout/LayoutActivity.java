package com.technology.waangyukui.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenvo on 2018/5/30.
 */

public class LayoutActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitle;
    private FrameLayout containerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        tvTitle = findViewById(R.id.tv_title);
        containerLayout = findViewById(R.id.container_layout);
        mFragmentManager=getSupportFragmentManager();
        initFragments();//初始化fragment
    }

    /**
     * 初始化fragment
     */
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments = new ArrayList<>();//存储所有的Fragment对象
    private List<String> mManagerNames = new ArrayList<>();//存储与Fragment对应的LayoutManager的名称

    private Fragment mCurrentFragment;
    private void initFragments() {
        EchelonFragment echelonFragment = new EchelonFragment();//梯形布局
        mFragments.add(echelonFragment);
        mManagerNames.add("EchelonLayoutManager");

        PickerFragment pickerFragment = new PickerFragment();//选择器布局
        mFragments.add(pickerFragment);
        mManagerNames.add("PickerLayoutManager");

        SlideFragment slideFragment = new SlideFragment();//滑动布局
        mFragments.add(slideFragment);
        mManagerNames.add("SlideLayoutManager");

        mFragmentManager.beginTransaction()
                .add(R.id.container_layout, mFragments.get(0))
                .add(R.id.container_layout,mFragments.get(1))
                .add(R.id.container_layout,mFragments.get(2))
                .hide(mFragments.get(2))
                .hide(mFragments.get(1))
                .show(mFragments.get(0))
                .commit();
        mCurrentFragment = mFragments.get(0);
        tvTitle.setText(mManagerNames.get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_0:
                switchFragment(0);
                break;
            case R.id.item_1:
                switchFragment(1);
                break;
            case R.id.item_2:
                switchFragment(2);
                break;
            case R.id.item_3:
                startActivity(new Intent(getApplicationContext(),SkidRightActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(int position) {
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .hide(mCurrentFragment)
                .show(mFragments.get(position))
                .commit();
        mCurrentFragment = mFragments.get(position);
       tvTitle.setText(mManagerNames.get(position));
    }
}
