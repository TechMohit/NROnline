package grossary.cyron.com.grossary.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.custom.CirclePageIndicator;


public class HomeFragment extends Fragment {

    private CirclePageIndicator indicator;
    private ViewPager pager;
    private static final Integer[] IMAGES = {R.drawable.logo_long, R.drawable.ic_launcher_background, R.drawable.logo_long,
            R.drawable.logo_long};
    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    private Timer timer;

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
        setViewPagerTimer();


        return view;
    }

    private void initView(View view) {

        indicator = view.findViewById(R.id.indicator);
        pager = view.findViewById(R.id.pager);

    }

    private void setViewPagerTimer() {
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);
        pager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));
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
                        pager.setCurrentItem((pager.getCurrentItem() + 1) % ImagesArray.size(), true);
                    }
                });
            }
        };
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
}
