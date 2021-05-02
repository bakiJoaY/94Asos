package com.example.yao.a94Asos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String mUser;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static List<Campaign> myCampaignItem = new ArrayList<>();
    public static List<Demand> myDemandItem = new ArrayList<>();
    public static List<Campaign> myReleasedCampaignItem = new ArrayList<>();
    public static List<Demand> myReleasedDemandItem = new ArrayList<>();
    public static List<VIPCampaign> myVIPCampaignItem = new ArrayList<>();
    public static List<Bitmap> myVIPCampaignItemBitmap = new ArrayList<>();
    public static List<String> myVIPCampaignItemUrl = new ArrayList<>();
    public static List<VIPDemand> myVIPDemandItem = new ArrayList<>();
    public static List<Bitmap> myVIPDemandItemBitmap = new ArrayList<>();
    public static List<String> myVIPDemandItemUrl = new ArrayList<>();
    public static List<Messages> myMessages = new ArrayList<>();
    public static List<Fragment> myFragment = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        mUser = intent.getStringExtra("com.example.yao.94Asos.USERNAME");
        TextView userTV = (TextView) findViewById(R.id.main_name);
        userTV.setText(mUser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myFragment.clear();
        myFragment.add(new RecFragment());
        myFragment.add(new AdFragment());
        myFragment.add(new CampaignFragment());
        myFragment.add(new DemandFragment());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.app_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
            Context context = getApplicationContext();
            BmobUser bmobUser = BmobUser.getCurrentUser();
            BmobUser.logOut();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_submit) {
            new AlertDialog.Builder(MainActivity.this).setTitle("选择类型")
                    .setPositiveButton("活动", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentCampaign = new Intent();
                            intentCampaign.putExtra("com.example.yao.94Asos.USERNAME", mUser);
                            intentCampaign.setClass(MainActivity.this, SubmitCampaignActivity.class);
                            startActivity(intentCampaign);
                        }
                    }).setNegativeButton("商业需求", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intentDemand = new Intent();
                    intentDemand.putExtra("com.example.yao.94Asos.USERNAME", mUser);
                    intentDemand.setClass(MainActivity.this, SubmitDemandActivity.class);
                    startActivity(intentDemand);
                }
            }).show();
        } else if (id == R.id.nav_released_demand) {
            Intent intent = new Intent(MainActivity.this, ReleasedDemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_released_campaign) {
            Intent intent = new Intent(MainActivity.this, ReleasedCamActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intentSetting = new Intent(this, SettingsActivity.class);
            startActivity(intentSetting);
        } else if (id == R.id.nav_contactUs) {
            Intent intentContactUs = new Intent(this, ContactUsActivity.class);
            intentContactUs.putExtra("com.example.yao.94Asos.USERNAME", mUser);
            startActivity(intentContactUs);
        } else if (id == R.id.nav_message) {
            Intent intentMessage = new Intent(this,MyMessageActivity.class);
            startActivity(intentMessage);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            /*return PlaceholderFragment.newInstance(position + 1);*/
            switch(position){
                case 0:
                    return myFragment.get(0);
                case 1:
                    return myFragment.get(1);
                case 2:
                    return myFragment.get(2);
                case 3:
                    return myFragment.get(3);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "首页";
                case 1:
                    return "广告";
                case 2:
                    return "热门活动";
                case 3:
                    return "商业活动";
            }
            return null;
        }
    }

    public void refresh(View v) {
        //TODO:title of the need
        finish();
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

}
