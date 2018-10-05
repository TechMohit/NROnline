package grossary.cyron.com.grossary.offers;


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
public class OffersFragment extends Fragment implements OnItemClickListener<OffersModel> {

    private RecyclerView recyclerView;
    private ArrayList<OffersModel> offersList = new ArrayList<>();

    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        initView(view);

        OffersModel obj = new OffersModel();
        obj.url = "dummyurl";
        offersList.add(obj);
        offersList.add(obj);
        offersList.add(obj);
        offersList.add(obj);
        offersList.add(obj);
        recyclerView.setAdapter(new OffersListAdapter(offersList, getActivity(), this));
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    @Override
    public void onItemClick(OffersModel OffersModel, View view, int position) {

    }
}

