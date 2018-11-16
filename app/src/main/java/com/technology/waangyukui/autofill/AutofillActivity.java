package com.technology.waangyukui.autofill;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.technology.waangyukui.autofill.commoncase.CommoncasesFragment;
import com.technology.waangyukui.autofill.edgecases.EdgecasesFragment;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/1.
 */

public class AutofillActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_fill);
        tabLayout = findViewById(R.id.table_layout);
        viewPager = findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new ScreenSlideAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class ScreenSlideAdapter extends FragmentPagerAdapter {
        private BaseMainFragment[] fragments = new BaseMainFragment[]{
                new CommoncasesFragment(), new EdgecasesFragment()
        };

        private final Context context;

        public ScreenSlideAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return context.getString(fragments[position].getPagerTitleId());
        }
    }

}
