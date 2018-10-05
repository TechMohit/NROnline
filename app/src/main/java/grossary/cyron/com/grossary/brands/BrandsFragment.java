package grossary.cyron.com.grossary.brands;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandsFragment extends Fragment implements OnItemClickListener<BrandsModel> {

    private RecyclerView recyclerView;
    private ArrayList<BrandsModel> brandsList = new ArrayList<>();

    public BrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brands, container, false);
        initView(view);

        BrandsModel obj = new BrandsModel();
        obj.tittle = "test";
        brandsList.add(obj);
        brandsList.add(obj);
        brandsList.add(obj);
        brandsList.add(obj);
        brandsList.add(obj);
        recyclerView.setAdapter(new BrandsListAdapter(brandsList, getActivity(), this));
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(BrandsModel brandsModel, View view, int position) {

    }
}
