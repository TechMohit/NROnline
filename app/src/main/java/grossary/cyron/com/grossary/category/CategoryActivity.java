package grossary.cyron.com.grossary.category;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import grossary.cyron.com.grossary.HomeActivity;
import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.adress.AddressFragment;
import grossary.cyron.com.grossary.cart.ViewCartFragment;
import grossary.cyron.com.grossary.order.MyOrderDetailFragment;
import grossary.cyron.com.grossary.order.MyOrdersFragment;
import grossary.cyron.com.grossary.payment.SubmitTransactionModel;
import grossary.cyron.com.grossary.utility.FragmentHelper;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.PreferenceManager;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ADDRESS;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ORDER;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ORDER_DETAIL;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.VIEW_CART;
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.MAKE_PAYMENT;
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.ADDRESS_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.MY_ORDER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SEARCH_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.VIEW_CART_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;
import static grossary.cyron.com.grossary.utility.Util.openKeyPad;

public class CategoryActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private LoadingView load;
    private RelativeLayout revBottom;
    public TextView txtCheckout, tvTotal, tvCount;
    private Dialog dialog;
    private ImageView tvBack,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        txtCheckout = findViewById(R.id.txtCheckout);
        revBottom = findViewById(R.id.revBottom);
        tvTotal = findViewById(R.id.tvTotal);
        tvCount = findViewById(R.id.tvCount);
        tvBack=findViewById(R.id.tvBack);
        img_cart=findViewById(R.id.img_cart);

        String current = getIntent().getStringExtra(CURRENT_FRG);
        if (current.equalsIgnoreCase(HOME_FRG)) {
            selectFrag(LIST, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(OFFER_FRG)) {
            selectFrag(LIST_DETAILS, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(SELLER_FRG)) {
            selectFrag(LIST, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(VIEW_CART_FRG)) {
            selectFrag(VIEW_CART, "1", VIEW_CART_FRG);
        } else if (current.equalsIgnoreCase(MY_ORDER_FRG)) {
            selectFrag(ORDER, "", MY_ORDER_FRG);
        } else if (current.equalsIgnoreCase(SEARCH_FRG)) {
            selectFrag(LIST, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);

        }
        getFragmentManager().addOnBackStackChangedListener(this);

        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtCheckout.getText().toString().equalsIgnoreCase(CHECKOUT))
                    selectFrag(VIEW_CART, "2", VIEW_CART_FRG);
                else if (txtCheckout.getText().toString().equalsIgnoreCase(PLACE_YOUR_ORDER))
                    selectFrag(ADDRESS, "", ADDRESS_FRG);
                else if(txtCheckout.getText().toString().equalsIgnoreCase(MAKE_PAYMENT)) {
                    AddressFragment fragment = (AddressFragment) FragmentHelper.getFragment(CategoryActivity.this, ADDRESS);
                    if(fragment!=null)
                    fragment.callApiSubmitTransaction();
                }
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCheckout.getText().toString().equalsIgnoreCase(CHECKOUT))
                    selectFrag(VIEW_CART, "2", VIEW_CART_FRG);

            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageView imgSearch=findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(CategoryActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.custom_search);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);
                dialog.setCancelable(true);
                TextView imgSearch = dialog.findViewById(R.id.imgSearch);
                ImageView imgBack = dialog.findViewById(R.id.imgBack);
                final EditText etSearch = dialog.findViewById(R.id.etSearch);

                openKeyPad(CategoryActivity.this,etSearch);

                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                imgSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (etSearch.getText().toString().equalsIgnoreCase("")) {
                            Toast.makeText(CategoryActivity.this, "Enter Value to Search", Toast.LENGTH_SHORT).show();
                        } else {

                            dialog.dismiss();
                            selectFrag(LIST, etSearch.getText().toString(), SEARCH_FRG);

                        }
                    }
                });
                dialog.show();

            }
        });
    }

    public void selectFrag(String tag, String response, String current) {

        revBottom.setVisibility(View.VISIBLE);
        Fragment fragment = null;
        Bundle arguments = null;
        switch (tag) {
            case LIST:

                fragment = new CategoryListFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);

                FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);


                break;
            case LIST_DETAILS:

                fragment = new CategoryListDetailsFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                if (current.equalsIgnoreCase(OFFER_FRG)) {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                } else {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                }
                break;
            case VIEW_CART:

                fragment = new ViewCartFragment();
                if (response.equalsIgnoreCase("1")) {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                } else {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                }

                break;
            case ORDER:
                revBottom.setVisibility(View.GONE);
                fragment = new MyOrdersFragment();
                FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                break;

            case ADDRESS:
                fragment = new AddressFragment();
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;
            case ORDER_DETAIL:
                revBottom.setVisibility(View.GONE);
                fragment = new MyOrderDetailFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;
        }
    }

    public void callApiAddtoCart(String productDescId, String productId, String stroeId, String sellingPrice, String qty) {

        load = new LoadingView(CategoryActivity.this);
        load.setCancalabe(false);
        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/AddToCartDetails";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(CategoryActivity.this).getLoginModel();


        Call<AddToCartDetailsModel> call = RetrofitClient.getAPIInterface().addToCartDetails(url, "" + res.getUserid(),
                "" + productDescId,
                "" + productId,
                "" + stroeId,
                "" + sellingPrice, "" + qty);
        Request request = new RetrofitRequest<>(call, new ResponseListener<AddToCartDetailsModel>() {
            @Override
            public void onResponse(int code, AddToCartDetailsModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    callApiCount();
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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


    public void callApiCount() {

//        load = new LoadingView(CategoryActivity.this);
//        load.setCancalabe(false);
//        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/ViewCartItemCountDetails";

        Log.e("URl", "*** " + url);
        final LoginModel res = new PreferenceManager(CategoryActivity.this).getLoginModel();


        Call<ViewCartItemCountDetailsModel> call = RetrofitClient.getAPIInterface().viewCartItemCountDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewCartItemCountDetailsModel>() {
            @Override
            public void onResponse(int code, ViewCartItemCountDetailsModel response, Headers headers) {
//                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    tvTotal.setText("₹" + response.getGrandtoal());
                    tvCount.setText("" + response.getTotalitemcount());
                    new PreferenceManager(CategoryActivity.this).setCount(""+response.getTotalitemcount());

                } else {
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {
//                load.dismissLoading();

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
//                load.dismissLoading();
            }
        });
        request.enqueue();
    }

    @Override
    public void onBackStackChanged() {

    }


}
