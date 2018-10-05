package grossary.cyron.com.grossary.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grossary.cyron.com.grossary.R;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Integer[] imageId = {R.drawable.logo_long, R.drawable.logo_long, R.drawable.logo_long, R.drawable.logo_long};
    String[] imagesName = {"image1","image2","image3","image4"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }


}
