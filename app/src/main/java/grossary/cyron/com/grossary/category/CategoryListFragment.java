package grossary.cyron.com.grossary.category;


import android.content.Context;
import android.content.Intent;
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
import grossary.cyron.com.grossary.account.SigninActivity;
import grossary.cyron.com.grossary.account.VerifyRegisterOTPModel;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.profile.ProfileActivity;
import grossary.cyron.com.grossary.sellers.SellersListAdapter;
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
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListFragment extends Fragment implements OnItemClickListener<CategoryModel.Projectlist> {

    private RecyclerView recyclerView;
    private CategoryListAdapter adapter;
    private LoadingView load;
    private Context context;
    public CategoryListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
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
        String url = BASE_URL + "/Home/ProductDetails";

        Log.e("URl", "*** " + url);
        String value=(getArguments().getString(FRAG_PARAMETER));
        String current=(getArguments().getString(CURRENT_FRG));

        String storeId="0",productId="0";

        if(current.equalsIgnoreCase(HOME_FRG)) {
            HomeModel.Objcategorylist product = new Gson().fromJson(value, HomeModel.Objcategorylist.class);
            productId=""+product.catergoryid;
        }else if(current.equalsIgnoreCase(SELLER_FRG)){
            HomeModel.Objstoredetailslist store = new Gson().fromJson(value, HomeModel.Objstoredetailslist.class);
            storeId=""+store.storeid;
        }
        Call<CategoryModel> call = RetrofitClient.getAPIInterface().productDetails(url, storeId,productId);
        Request request = new RetrofitRequest<>(call, new ResponseListener<CategoryModel>() {
            @Override
            public void onResponse(int code, CategoryModel response, Headers headers) {
                load.dismissLoading();
                if (response.response.responseval) {
                    adapter.setAdapterData(response.projectlists);

                }else{
                    Toast.makeText(getActivity(), ""+response.response.reason , Toast.LENGTH_SHORT).show();
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
        adapter = new CategoryListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);

    }

    @Override
    public void onItemClick(CategoryModel.Projectlist categoryModel, View view, int position,String type) {

        if(type.equalsIgnoreCase(ONCLICK)) {
            ((CategoryActivity) getActivity()).selectFrag(LIST_DETAILS, new Gson().toJson(categoryModel), CATG_LIST_FRG);
        }else if(type.equalsIgnoreCase(ADD)){
            ((CategoryActivity)getActivity()).callApiAddtoCart(""+categoryModel.productDescId,""+categoryModel.productId,""+categoryModel.stroeId,categoryModel.sellingPrice);
        }

    }

}
