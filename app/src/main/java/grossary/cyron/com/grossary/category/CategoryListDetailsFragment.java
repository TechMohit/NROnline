package grossary.cyron.com.grossary.category;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.GlideApp;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryListDetailsFragment extends Fragment {


    private TextView tvProductName, tvDesc, tvSellingPrice, tvMrpPrice, txtCount;
    private LoadingView load;
    private Context context;
    private int count = 1;
    private Button btnAddCart, btnMin, btnAdd;
    private ImageView imgProduct;
    private ProductdDescDetailsModel responseMain;

    public CategoryListDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list_details, container, false);
        initView(view);

        ((CategoryActivity)getActivity()).txtCheckout.setText(CHECKOUT);

        txtCount.setText("" + count);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txtCount.setText("" + count);

            }
        });
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count >=2) {
                    count--;
                    txtCount.setText("" + count);
                }

            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CategoryActivity) getActivity()).callApiAddtoCart("" + responseMain.productDescId,
                        "" + responseMain.productId, "" + responseMain.storeId, ""+responseMain.shippingCharges,""+count);

            }
        });


        return view;
    }

    private void initView(View view) {
        tvProductName = view.findViewById(R.id.tvProductName);
        tvDesc = view.findViewById(R.id.tvDesc);
        tvSellingPrice = view.findViewById(R.id.tvSellingPrice);
        tvMrpPrice = view.findViewById(R.id.tvMrpPrice);
        btnAddCart = view.findViewById(R.id.btnAddCart);
        btnMin = view.findViewById(R.id.btnMin);
        btnAdd = view.findViewById(R.id.btnAdd);
        txtCount = view.findViewById(R.id.txtCount);
        imgProduct = view.findViewById(R.id.imgProduct);
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
        String url = BASE_URL + "/Home/ProductdDescDetails";

        Log.e("URl", "*** " + url);

        String value = (getArguments().getString(FRAG_PARAMETER));
        String current = (getArguments().getString(CURRENT_FRG));

        String productId = "0";

        if (current.equalsIgnoreCase(CATG_LIST_FRG)) {
            CategoryModel.Projectlist product = new Gson().fromJson(value, CategoryModel.Projectlist.class);
            productId = "" + product.productDescId;
        } else if (current.equalsIgnoreCase(OFFER_FRG)) {
            HomeModel.Objofferdetailslist product = new Gson().fromJson(value, HomeModel.Objofferdetailslist.class);
            productId = "" + product.productdescid;
        }

        Call<ProductdDescDetailsModel> call = RetrofitClient.getAPIInterface().ProductdDescDetails(url, "" + productId);
        Request request = new RetrofitRequest<>(call, new ResponseListener<ProductdDescDetailsModel>() {
            @Override
            public void onResponse(int code, ProductdDescDetailsModel response, Headers headers) {
                load.dismissLoading();
                responseMain = response;
                if (response.response.responseval) {

                    tvProductName.setText(String.format("%s", response.productName));
                    tvDesc.setText(String.format("%s", response.subProductQTY));
                    tvSellingPrice.setText("₹" + String.format("%s", response.sellingPrice));
                    tvMrpPrice.setText(String.format("%s", "(₹" + response.mRPPrice + ")"));
                    tvMrpPrice.setPaintFlags(tvMrpPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    GlideApp.with(getActivity())
                            .load(response.productImage)
                            .centerInside()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.drawable.logo_long)
                            .error(R.drawable.ic_launcher_background)
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(imgProduct);


                } else {
                    Toast.makeText(getActivity(), "" + response.response.reason, Toast.LENGTH_SHORT).show();
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
