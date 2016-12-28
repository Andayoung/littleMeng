package com.gg.nnr.littlemeng.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.gg.nnr.littlemeng.R;
import com.gg.nnr.littlemeng.db.DBManager;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.SmartTabLayout;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItem;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItemAdapter;
import com.xiaochao.lcrapiddeveloplibrary.SmartTab.UtilsV4.v4.FragmentPagerItems;



public class MainActivity extends AppCompatActivity {
    DBManager mgr;
    ViewGroup tab;
    ViewPager viewPager;
    Button btnChangeItem;
    SmartTabLayout viewPagerTab;
    FragmentPagerItemAdapter adapter;
    FragmentPagerItems pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindView();
    }

    private void initFindView() {
        tab = (ViewGroup) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        btnChangeItem = (Button) findViewById(R.id.btn_change_item);
        tab.addView(LayoutInflater.from(this).inflate(R.layout.tab_top_layout, tab, false));

        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        pages = new FragmentPagerItems(this);
        mgr = new DBManager(this);
        for (String item : mgr.query("1")) {
            pages.add(FragmentPagerItem.of(item, TabFragment.class));
        }

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        btnChangeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeItemActivity.class);
                startActivityForResult(intent, 8);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pages.clear();
        if (resultCode == 0) {
            for (String item : mgr.query("1")) {
                pages.add(FragmentPagerItem.of(item, TabFragment.class));
            }
            adapter = new FragmentPagerItemAdapter(
                    getSupportFragmentManager(), pages);
            viewPager.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            viewPagerTab.setViewPager(viewPager);
        }
    }
}
