package grossary.cyron.com.grossary.brands;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossary.HomeActivity;
import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.sellers.SellersListAdapter;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends Fragment implements OnItemClickListener<HomeModel.ObjOfferImageList> {

    private RecyclerView recyclerView;
    private ArrayList<HomeModel.ObjOfferImageList> brandsList = new ArrayList<>();
    private List<HomeModel.ObjOfferImageList> data;
    private BrandsListAdapter adapter;

    public BrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands, container, false);
        initView(view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        setAdapter();
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(adapter.getItemCount()<=0)
        {

        }
    }

    private void setAdapter() {
        adapter = new BrandsListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(HomeModel.ObjOfferImageList brandsModel, View view, int position) {

    }

    public void setData() {


    }

    public List<HomeModel.ObjOfferImageList> getData() {
        return data;
    }
}
