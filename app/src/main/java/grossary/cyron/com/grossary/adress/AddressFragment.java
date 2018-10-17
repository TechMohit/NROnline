package grossary.cyron.com.grossary.adress;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.category.CategoryActivity;
import grossary.cyron.com.grossary.order.MyOrdersAdapter;
import grossary.cyron.com.grossary.order.ViewOrderListModel;
import grossary.cyron.com.grossary.profile.GetUserProfileModel;
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

import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.MAKE_PAYMENT;
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment  {


    private LoadingView load;
    private TextInputEditText etUserName,etEmail,etAddress,etCity,etState,etZip,etPhone;

    private Context context;
    public AddressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ((CategoryActivity)getActivity()).txtCheckout.setText(MAKE_PAYMENT);
        etUserName=view.findViewById(R.id.etUserName);
        etEmail=view.findViewById(R.id.etEmail);
        etAddress=view.findViewById(R.id.etAddress);
        etCity=view.findViewById(R.id.etCity);
        etState=view.findViewById(R.id.etState);
        etZip=view.findViewById(R.id.etZip);
        etPhone=view.findViewById(R.id.etPhone);
        callApiProfile();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    private void callApiProfile() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Profile/GetUserProfile";
        final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Log.e("URl", "*** " + url);
        Call<GetUserProfileModel> call = RetrofitClient.getAPIInterface().getUserProfile(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<GetUserProfileModel>() {
            @Override
            public void onResponse(int code, GetUserProfileModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    etUserName.setText(""+response.getFullname());
                    etEmail.setText(""+response.getEmail());
                    etAddress.setText(""+response.getAddress());
                    etCity.setText(""+response.getCity());
                    etState.setText(""+response.getState());
                    etZip.setText(""+response.getZipcode());
                    etPhone.setText(""+response.getMobileno());

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
