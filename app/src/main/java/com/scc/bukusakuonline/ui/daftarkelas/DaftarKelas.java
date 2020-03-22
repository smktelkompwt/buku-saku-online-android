package com.scc.bukusakuonline.ui.daftarkelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.scc.bukusakuonline.R;
import com.scc.bukusakuonline.ui.MainActivity;

public class DaftarKelas extends AppCompatActivity {


    ViewPager mViewPager;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kelas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Daftar Kelas");
        mTitle.setText(toolbar.getTitle());
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(DaftarKelas.this, MainActivity.class);
            startActivity(intent);
        });

        TabLayout mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewpager);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mViewPager.setAdapter(new viewpageradapter(getSupportFragmentManager(), mTabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }



    public class viewpageradapter extends FragmentStatePagerAdapter {
        int mNumofTabs;

        @SuppressWarnings("deprecation")
        viewpageradapter(FragmentManager fragmentManager, int mNumOfTabs) {
            super(fragmentManager);
            this.mNumofTabs = mNumOfTabs;
        }

        @SuppressWarnings("ConstantConditions")
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RplFragment();
                case 1:
                    return new TkjFragment();
                case 2:
                    return new TjaFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumofTabs;
        }
    }
}
