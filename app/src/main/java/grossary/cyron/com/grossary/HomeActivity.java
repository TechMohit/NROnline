package grossary.cyron.com.grossary;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossary.brands.BrandsFragment;
import grossary.cyron.com.grossary.drawer.DrawerFragment;
import grossary.cyron.com.grossary.home.HomeFragment;
import grossary.cyron.com.grossary.offers.OffersFragment;
import grossary.cyron.com.grossary.profile.ProfileActivity;
import grossary.cyron.com.grossary.sellers.SellerFragment;
import grossary.cyron.com.grossary.tabs.OneFragment;
import grossary.cyron.com.grossary.utility.Constant;
import grossary.cyron.com.grossary.utility.FragmentHelper;

public class HomeActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener,DrawerFragment.DrawerListener, android.app.FragmentManager.OnBackStackChangedListener {

    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.tb_home,
            R.drawable.tb_offer,
            R.drawable.tb_seller,
            R.drawable.tb_brand
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, null, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Fragment drawerFrag;
                drawerFrag = FragmentHelper.getFragment(HomeActivity.this, "drawer");
                if (drawerFrag != null) {
                    ((DrawerFragment) drawerFrag).focus();
                }
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getFragmentManager().addOnBackStackChangedListener(this);
        FragmentHelper.addFragment(this, R.id.navigation_container, new DrawerFragment());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[0], 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Offers");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[1], 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Seller");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[2], 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Brands");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[3], 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment(), "Home");
        adapter.addFrag(new OffersFragment(), "Offers");
        adapter.addFrag(new SellerFragment(), "Seller");
        adapter.addFrag(new BrandsFragment(), "Brands");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void initView() {
        drawer=findViewById(R.id.drawer);
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void drawerOnItemClicked(String tag) {

        switch (tag){

            case Constant.NAV_DRAWER.MY_PROFILE:

               startActivity(new Intent(this,ProfileActivity.class));

                break;

        }

    }

}
