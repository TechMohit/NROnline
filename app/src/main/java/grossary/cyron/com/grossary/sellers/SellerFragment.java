package grossary.cyron.com.grossary.sellers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerFragment extends Fragment implements OnItemClickListener<HomeModel.Objstoredetailslist> {

    private RecyclerView recyclerView;
    private ArrayList<HomeModel.Objstoredetailslist> sellerList = new ArrayList<>();
    private SellersListAdapter adapter;

    public SellerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller, container, false);
        initView(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setAdapter();

        return view;
    }

    private void setAdapter() {
        adapter = new SellersListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(HomeModel.Objstoredetailslist sellersModel, View view, int position) {

    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {

            if (adapter == null)
                setAdapter();
            adapter.setAdapterData(sellerList);
        }
    }

    public void setData(List<HomeModel.Objstoredetailslist> sellerList) {

        if (this.sellerList.size() > 0)
            this.sellerList.clear();

        this.sellerList.addAll(sellerList);
    }

    public List<HomeModel.Objstoredetailslist> getData() {
        return sellerList;
    }
}
