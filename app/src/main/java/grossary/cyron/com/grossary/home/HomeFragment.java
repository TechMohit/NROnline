package grossary.cyron.com.grossary.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import grossary.cyron.com.grossary.HomeActivity;
import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.category.CategoryActivity;
import grossary.cyron.com.grossary.custom.CirclePageIndicator;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;


public class HomeFragment extends Fragment implements OnItemClickListener<HomeModel.Objcategorylist> {

    private CirclePageIndicator indicator;
    private ViewPager pager;
    private List<HomeModel.ObjOfferImageList> homeOfferList = new ArrayList<>();
    private ArrayList<HomeModel.Objcategorylist> homeList = new ArrayList<>();
    private Timer timer;
    private HomeListAdapter adapter;
    private RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setAdapter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(adapter.getItemCount()<=0)
        {
            if(((HomeActivity)getActivity()).getHomeModel()!=null)
            setData(((HomeActivity)getActivity()).getHomeModel().objcategorylist, ((HomeActivity)getActivity()).getHomeModel().homeOfferList);
        }
    }

    private void setAdapter() {
        adapter = new HomeListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_view);
        indicator = view.findViewById(R.id.indicator);
        pager = view.findViewById(R.id.pager);

    }

    private void setViewPagerTimer(final List<HomeModel.ObjOfferImageList> homeOfferList) {

        pager.setAdapter(new SlidingImage_Adapter(getActivity(), homeOfferList));
        indicator.setViewPager(pager);
        changePagerScroller();
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                pager.post(new Runnable() {

                    @Override
                    public void run() {
                        pager.setCurrentItem((pager.getCurrentItem() + 1) % homeOfferList.size(), true);
                    }
                });
            }
        };
        if(timer==null)
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);
    }


    private void changePagerScroller() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(pager.getContext());
            mScroller.set(pager, scroller);
        } catch (Exception e) {
        }
    }

    public void setData(List<HomeModel.Objcategorylist> data, List<HomeModel.ObjOfferImageList> homeOfferList) {


        if(adapter==null || data==null || homeOfferList==null)
            return;
        if(this.homeOfferList.size()>0)
            this.homeOfferList.clear();
        this.homeOfferList.addAll(homeOfferList);

        if(homeList.size()>0)
            homeList.clear();
        homeList.addAll(data);
        adapter.setAdapterData(homeList);

        setViewPagerTimer(this.homeOfferList);

    }

    public List<HomeModel.Objcategorylist> getData() {
        return homeList;
    }

    @Override
    public void onItemClick(HomeModel.Objcategorylist objstoredetailslist, View view, int position) {
        Intent intent=new Intent(getActivity(),CategoryActivity.class);
        intent.putExtra(ACT_HOME_PARAMETER,new Gson().toJson(objstoredetailslist));
        startActivity(intent);

    }
}
