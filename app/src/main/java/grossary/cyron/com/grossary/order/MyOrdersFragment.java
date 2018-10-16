package grossary.cyron.com.grossary.order;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import grossary.cyron.com.grossary.HomeActivity;
import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.category.CategoryActivity;
import grossary.cyron.com.grossary.category.CategoryListAdapter;
import grossary.cyron.com.grossary.category.CategoryModel;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.PreferenceManager;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ADD;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ONCLICK;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ORDER_DETAIL;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.MY_ORDER_DETAIL_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment implements OnItemClickListener<ViewOrderListModel.OrderlistEntity> {

    private RecyclerView recyclerView;
    private MyOrdersAdapter adapter;
    private LoadingView load;
    private Context context;
    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        initView(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        setAdapter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callApi();

    }

    private void callApi() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Order/ViewOrderList";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Call<ViewOrderListModel> call = RetrofitClient.getAPIInterface().viewOrderList(url,""+res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewOrderListModel>() {
            @Override
            public void onResponse(int code, ViewOrderListModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    adapter.setAdapterData(response.getOrderlist());

                }else{
                    Toast.makeText(getActivity(), ""+response.getResponse().getResponseval() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {
                load.dismissLoading();

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
                load.dismissLoading();
            }
        });
        request.enqueue();


    }

    private void setAdapter() {
        adapter = new MyOrdersAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);

    }

    @Override
    public void onItemClick(ViewOrderListModel.OrderlistEntity categoryModel, View view, int position,String type) {

        ((CategoryActivity)getActivity()).selectFrag(ORDER_DETAIL,new Gson().toJson(categoryModel),MY_ORDER_DETAIL_FRG);
    }

}
