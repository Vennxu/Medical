package com.example.administrator.medical.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.administrator.medical.R;
import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.pojo.NewsClassPojo;
import com.example.administrator.medical.sqlite.Dao.NewsClassDao;
import com.example.administrator.medical.ui.fragment.NewsFragment;
import com.example.administrator.medical.ui.widget.EmptyLayout;
import com.example.administrator.medical.ui.widget.PagerSlidingTabStrip;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private PagerSlidingTabStrip mSlidingTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainView();
        getFragment();
    }

    private void initMainView() {
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.main_strip);
        mToolbar = (Toolbar) findViewById(R.id.main_toorbar);
        mToolbar.setTitle(getString(R.string.title));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void getFragment(){
        NewsClassDao classDao = new NewsClassDao(this);
        ArrayList<NewsClassPojo> classPojos = (ArrayList<NewsClassPojo>) classDao.queryAll();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < classPojos.size(); i++){
            NewsFragment fragment = NewsFragment.newInstance(classPojos.get(i));
            fragments.add(fragment);
        }
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments, classPojos);
        mViewPager.setAdapter(mainFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mSlidingTabStrip.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MainFragmentAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragments;
        private ArrayList<NewsClassPojo> classPojos;

        public MainFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<NewsClassPojo> classPojos) {
            super(fm);
            this.fragments = fragments;
            this.classPojos = classPojos;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return classPojos.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return classPojos.get(position).getName();
        }
    }
}
