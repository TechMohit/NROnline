package grossary.cyron.com.grossary.offers;


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
            setData(((HomeActivity)getActivity()).getHomeModel().objofferdetailslist);
        }
    }

    private void setAdapter() {
        adapter = new OffersListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }



    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(HomeModel.Objofferdetailslist OffersModel, View view, int position) {

    }

    public void setData(List<HomeModel.Objofferdetailslist> offersList) {

        if(adapter==null || offersList==null)
            return;

        if (this.offersList.size() > 0)
            this.offersList.clear();
        this.offersList.addAll(offersList);
        adapter.setAdapterData(this.offersList);
    }

    public List<HomeModel.Objofferdetailslist> getData() {
        return offersList;
    }
}

