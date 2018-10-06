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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.account.SigninActivity;
import grossary.cyron.com.grossary.brands.BrandsFragment;
import grossary.cyron.com.grossary.drawer.DrawerFragment;
import grossary.cyron.com.grossary.home.HomeFragment;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.offers.OffersFragment;
import grossary.cyron.com.grossary.profile.ProfileActivity;
import grossary.cyron.com.grossary.sellers.SellerFragment;
import grossary.cyron.com.grossary.tabs.OneFragment;
import grossary.cyron.com.grossary.utility.Constant;
import grossary.cyron.com.grossary.utility.FragmentHelper;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

public class HomeActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener,DrawerFragment.DrawerListener, android.app.FragmentManager.OnBackStackChangedListener {

    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LoadingView load;
    private HomeModel homeModel;

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

        viewPager =findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        callHomeApi();


    }

    private void callHomeApi() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/HomeDetails";

        Log.e("URl", "*** " + url);
        Call<HomeModel> call = RetrofitClient.getAPIInterface().homeDetailsAPI(url,
                "9844332677");
        Request request = new RetrofitRequest<>(call, new ResponseListener<HomeModel>() {
            @Override
            public void onResponse(int code, HomeModel response, Headers headers) {
                load.dismissLoading();

                setHomeModel(response);

            }

            @Override
            public void onError(int error) {
                load.dismissLoading();

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("HomeActivity", "failure ---->");
                load.dismissLoading();
            }
        });
        request.enqueue();

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
        adapter.addFrag(new HomeFragment(), Constant.TABS.HOME);
        adapter.addFrag(new OffersFragment(), Constant.TABS.OFFER);
        adapter.addFrag(new SellerFragment(), Constant.TABS.SELLER);
        adapter.addFrag(new BrandsFragment(), Constant.TABS.BRANDS);
        viewPager.setAdapter(adapter);
    }

    public void setHomeModel(HomeModel response) {
        if(homeModel!=null)
            homeModel=new HomeModel();
        homeModel=response;
        ViewPagerAdapter fa = (ViewPagerAdapter)viewPager.getAdapter();
        OffersFragment theFragment = (OffersFragment)fa.getItem(1);
        SellerFragment sellerFragment = (SellerFragment)fa.getItem(2);
        theFragment.setData(homeModel.objofferdetailslist);
        sellerFragment.setData(response.objstoredetailslist);
    }

    public HomeModel getHomeModel() {
        return homeModel;
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
