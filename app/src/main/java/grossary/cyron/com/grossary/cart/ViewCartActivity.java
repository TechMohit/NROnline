package grossary.cyron.com.grossary.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.profile.GetUserProfileUpdateModel;
import grossary.cyron.com.grossary.profile.ProfileActivity;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.PreferenceManager;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

public class ViewCartActivity extends AppCompatActivity implements OnItemClickListener<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity> {

    private RecyclerView recyclerView;
    private ArrayList<ViewCartModel> list = new ArrayList<>();
    private ViewCartAdapter adapter;
    private LoadingView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        recyclerView = findViewById(R.id.recycle_view);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        setAdapter();
        callApiViewCart();

    }

    private void callApiViewCart() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/ViewAddtoCartDetails";
        LoginModel res = new PreferenceManager(ViewCartActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);

        Call<ViewAddtoCartDetailsModel> call = RetrofitClient.getAPIInterface().viewAddtoCartDetails(url,"1006" );
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewAddtoCartDetailsModel>() {
            @Override
            public void onResponse(int code, ViewAddtoCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    adapter.setAdapterData(response.getObjviewaddcartlist());
                } else {
                    Toast.makeText(ViewCartActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
        adapter = new ViewCartAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity obj, View view, int position) {

    }
}
