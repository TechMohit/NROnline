package grossary.cyron.com.grossary.category;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.cart.ViewCartFragment;
import grossary.cyron.com.grossary.order.MyOrdersFragment;
import grossary.cyron.com.grossary.utility.FragmentHelper;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.PreferenceManager;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ORDER;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.VIEW_CART;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.MY_ORDER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossary.utility.Constant.CURRENT_STATE.VIEW_CART_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

public class CategoryActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private LoadingView load;
    private RelativeLayout revBottom;
    private TextView txtCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
        }
        getFragmentManager().addOnBackStackChangedListener(this);
        txtCheckout=findViewById(R.id.txtCheckout);

        revBottom=findViewById(R.id.revBottom);
        TextView txtCheckout = findViewById(R.id.txtCheckout);
        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectFrag(VIEW_CART, "2", VIEW_CART_FRG);

            }
        });


    }

    public void selectFrag(String tag, String response, String current) {

        revBottom.setVisibility(View.VISIBLE);
        txtCheckout.setText("Checkout");
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
                txtCheckout.setText("Place Your Order");

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

        }
    }

    public void callApiAddtoCart(String productDescId, String productId, String stroeId, String sellingPrice) {

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
                "" + sellingPrice);
        Request request = new RetrofitRequest<>(call, new ResponseListener<AddToCartDetailsModel>() {
            @Override
            public void onResponse(int code, AddToCartDetailsModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
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

    @Override
    public void onBackStackChanged() {

    }
}
