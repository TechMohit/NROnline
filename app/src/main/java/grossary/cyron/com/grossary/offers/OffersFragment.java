package grossary.cyron.com.grossary.offers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class OffersFragment extends Fragment implements OnItemClickListener<HomeModel.Objofferdetailslist> {

    private RecyclerView recyclerView;
    private ArrayList<HomeModel.Objofferdetailslist> offersList = new ArrayList<>();
    private OffersListAdapter adapter;

    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        initView(view);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        adapter = new OffersListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {

            if (adapter == null)
                setAdapter();
            adapter.setAdapterData(offersList);
        }
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(HomeModel.Objofferdetailslist OffersModel, View view, int position) {

    }

    public void setData(List<HomeModel.Objofferdetailslist> offersList) {
        if (this.offersList.size() > 0)
            this.offersList.clear();
        this.offersList.addAll(offersList);
    }

    public List<HomeModel.Objofferdetailslist> getData() {
        return offersList;
    }
}

