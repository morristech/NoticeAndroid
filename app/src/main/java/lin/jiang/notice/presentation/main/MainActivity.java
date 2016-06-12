package lin.jiang.notice.presentation.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import lin.jiang.notice.R;
import lin.jiang.notice.presentation.base.BaseActivtiy;
import lin.jiang.notice.presentation.componet.ViewPagerIndicator;
import lin.jiang.notice.presentation.drawer.DrawerFragment;
import lin.jiang.notice.presentation.newslist.NewsFragment;

public class MainActivity extends BaseActivtiy implements View.OnClickListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id._main_toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id._main_wrapper);
        toolbar.setNavigationIcon(R.drawable._main_menu);
        toolbar.setNavigationOnClickListener(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id._main_viewpager);
        ViewPagerIndicator indicator = (ViewPagerIndicator) findViewById(R.id._main_indocator);
        viewPager.setOffscreenPageLimit(MainNewsPageAdapter.TAB_NAME.length);
        viewPager.setAdapter(new MainNewsPageAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager);
        getSupportFragmentManager().beginTransaction().replace(R.id._main_drawer, DrawerFragment.newInstance()).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            SearchActivity.start(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        drawer.openDrawer(GravityCompat.START);
    }


    private static class MainNewsPageAdapter extends FragmentPagerAdapter {
        public static String TAB_NAME[] = new String[]{"推荐", "最新", "新闻", "通知"};
        private NewsFragment[] fragments = new NewsFragment[TAB_NAME.length];
        {
            for (int i = 0; i < TAB_NAME.length; i++)
                fragments[i] = NewsFragment.newInstance(i);
        }
        public MainNewsPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        public Fragment getItem(int i) {
            return fragments[i];
        }
        public int getCount() {
            return TAB_NAME.length;
        }
        public CharSequence getPageTitle(int i) {
            return TAB_NAME[i % TAB_NAME.length];
        }
    }
}
