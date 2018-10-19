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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.category.CategoryActivity;
import grossary.cyron.com.grossary.order.MyOrdersAdapter;
import grossary.cyron.com.grossary.order.ViewOrderListModel;
import grossary.cyron.com.grossary.payment.SubmitTransactionModel;
import grossary.cyron.com.grossary.profile.GetUserProfileModel;
import grossary.cyron.com.grossary.profile.ProfileActivity;
import grossary.cyron.com.grossary.utility.FragmentHelper;
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
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.MAKE_PAYMENT_ONLINE;
import static grossary.cyron.com.grossary.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {


    private LoadingView load;
    private TextInputEditText etUserName, etAddress, etCity, etState, etZip, etPhone;
    private RadioButton rdOnline, rdCash;
    private Context context;
    private CompoundButton.OnCheckedChangeListener rdOnlineListener, rdCashListener;

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ((CategoryActivity) getActivity()).txtCheckout.setText(MAKE_PAYMENT);
        etUserName = view.findViewById(R.id.etUserName);
        etAddress = view.findViewById(R.id.etAddress);
        etCity = view.findViewById(R.id.etCity);
        etState = view.findViewById(R.id.etState);
        etZip = view.findViewById(R.id.etZip);
        etPhone = view.findViewById(R.id.etPhone);
        rdCash = view.findViewById(R.id.rdCash);
        rdOnline = view.findViewById(R.id.rdOnline);
        callApiProfile();

        rdCashListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rdOnline.setOnCheckedChangeListener(null);
                if (isChecked) {
                    rdOnline.setChecked(false);
                } else {
                    rdOnline.setChecked(true);

                }
                rdOnline.setOnCheckedChangeListener(rdOnlineListener);
            }
        };

        rdOnlineListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                rdCash.removeOnLayoutChangeListener(null);
                if (isChecked) {
                    rdCash.setChecked(false);
                } else {
                    rdCash.setChecked(true);

                }
                rdCash.setOnCheckedChangeListener(rdCashListener);

            }
        };
        rdCash.setOnCheckedChangeListener(rdCashListener);
        rdOnline.setOnCheckedChangeListener(rdOnlineListener);
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

                    etUserName.setText("" + response.getFullname());
                    etAddress.setText("" + response.getAddress());
                    etCity.setText("" + response.getCity());
                    etState.setText("" + response.getState());
                    etZip.setText("" + response.getZipcode());
                    etPhone.setText("" + response.getMobileno());

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

    public void callApiSubmitTransaction() {
        if (etUserName.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(context, "Enter Full Name", Toast.LENGTH_SHORT).show();
        } else if(etAddress.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, "Enter Address", Toast.LENGTH_SHORT).show();
        }else if(etCity.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, "Enter City", Toast.LENGTH_SHORT).show();
        }else if(etState.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, "Enter State", Toast.LENGTH_SHORT).show();
        }else if(etZip.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, "Enter Zip Code", Toast.LENGTH_SHORT).show();
        }else if(etPhone.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(context, "Enter Phone", Toast.LENGTH_SHORT).show();
        }else if (rdCash.isChecked()) {
            String fullName = etUserName.getText().toString();
            String address = etAddress.getText().toString();
            String city = etCity.getText().toString();
            String state = etState.getText().toString();
            String zipcode = etZip.getText().toString();
            String phone = etPhone.getText().toString();
            String paymode = MAKE_PAYMENT_ONLINE;
            String shippinfCharge = new PreferenceManager(getActivity()).getShippingCharges();
            String totalCharge = new PreferenceManager(getActivity()).getCount();

            load = new LoadingView(getActivity());
            load.setCancalabe(false);
            load.showLoading();

            String url = BASE_URL + "/Home/SubmitTransaction";

            Log.e("URl", "*** " + url);
            final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();


            Call<SubmitTransactionModel> call = RetrofitClient.getAPIInterface().submitTransaction(url, fullName, address, city, state, zipcode, phone
                    , "" + res.getUserid(), paymode, shippinfCharge, totalCharge);
            Request request = new RetrofitRequest<>(call, new ResponseListener<SubmitTransactionModel>() {
                @Override
                public void onResponse(int code, SubmitTransactionModel response, Headers headers) {
                    load.dismissLoading();
                    if (response.getResponse().getResponseval()) {
                        Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                        new PreferenceManager(getActivity()).setShippingCharges("0");
                        new PreferenceManager(getActivity()).setCount("0");
                        getActivity().finish();

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
        } else {
            Toast.makeText(context, "Pay Online", Toast.LENGTH_SHORT).show();
//            getActivity().finish();

        }
    }

}
