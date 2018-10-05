package grossary.cyron.com.grossary.sellers;


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
public class SellerFragment extends Fragment implements OnItemClickListener<SellersModel> {

    private RecyclerView recyclerView;
    private ArrayList<SellersModel> sellerList = new ArrayList<>();

    public SellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_seller, container, false);
        initView(view);

        SellersModel obj = new SellersModel();
        obj.tittle = "test";
        sellerList.add(obj);
        sellerList.add(obj);
        sellerList.add(obj);
        sellerList.add(obj);
        sellerList.add(obj);
        recyclerView.setAdapter(new SellersListAdapter(sellerList, getActivity(), this));
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(SellersModel sellersModel, View view, int position) {

    }
}
