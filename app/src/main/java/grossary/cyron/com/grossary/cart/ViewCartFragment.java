package grossary.cyron.com.grossary.cart;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

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
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.DELETE;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ONCLICK;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCartFragment extends Fragment implements OnItemClickListener<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity>  {

    private RecyclerView recyclerView;
    private ArrayList<ViewCartModel> list = new ArrayList<>();
    private ViewCartAdapter adapter;
    private LoadingView load;
    private Context context;
    
    public ViewCartFragment() {
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
        // Inflate the layout for getActivity() fragment
        View view = inflater.inflate(R.layout.fragment_view_cart, container, false);
        initView(view);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        callApiViewCart();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);

    }

    private void callApiViewCart() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/ViewAddtoCartDetails";
        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Log.e("URl", "*** " + url);

        Call<ViewAddtoCartDetailsModel> call = RetrofitClient.getAPIInterface().viewAddtoCartDetails(url,""+res.getUserid() );
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewAddtoCartDetailsModel>() {
            @Override
            public void onResponse(int code, ViewAddtoCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    adapter.setAdapterData(response.getObjviewaddcartlist());
                } else {
                    Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
        adapter = new ViewCartAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity obj, View view, int position,String type) {

        if(type.equalsIgnoreCase(DELETE)){
            callApiDeleteCart(obj);
        }

    }

    private void callApiDeleteCart(ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity obj) {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/DeleteFromCartDetails";

        Log.e("URl", "*** " + url);

        Call<DeleteFromCartDetailsModel> call = RetrofitClient.getAPIInterface().deleteFromCartDetails(url,""+obj.getOrderid() );
        Request request = new RetrofitRequest<>(call, new ResponseListener<DeleteFromCartDetailsModel>() {
            @Override
            public void onResponse(int code, DeleteFromCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    callApiViewCart();
                    Toast.makeText(getActivity(), "" + response.getStatusmessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

}
