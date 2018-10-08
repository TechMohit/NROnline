package grossary.cyron.com.grossary.account;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import grossary.cyron.com.grossary.HomeActivity;
import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.PreferenceManager;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

public class SigninActivity extends AppCompatActivity {


    private TextView txt_register;
    private Button btn_login;
    private LoadingView load;
    private EditText etPhone;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        txt_register = findViewById(R.id.txt_register);
        etPhone = findViewById(R.id.etPhone);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    callApiAuthenticate();
                }
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhone.setText("");
                startActivity(new Intent(SigninActivity.this, SignupActivity.class));
            }
        });

        if (new PreferenceManager(SigninActivity.this).getAutoLogin()) {
            startActivity(new Intent(SigninActivity.this, HomeActivity.class));
            finish();
        }

    }

    private void callApiAuthenticate() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/Authenticate";

        Log.e("URl", "*** " + url);
        Call<LoginModel> call = RetrofitClient.getAPIInterface().authenticate(url,
                etPhone.getText().toString());
        Request request = new RetrofitRequest<>(call, new ResponseListener<LoginModel>() {
            @Override
            public void onResponse(int code, LoginModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    openDilogotp(response);
                } else {
                    Toast.makeText(SigninActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    private void openDilogotp(final LoginModel response) {

        dialog = new Dialog(SigninActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_otp);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.show();
        final PinEntryEditText pin=dialog.findViewById(R.id.pin);

        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btn_retry = dialog.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText("");
                callApiRetry();
            }
        });
        pin.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                if(str!=null && str.length()==6){
                    callApiOtpVerify(str.toString(),response);
                }
            }
        });
    }
    private void callApiRetry() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/ResendOTP";

        Log.e("URl", "*** " + url);
        Call<ResendOTPModel> call = RetrofitClient.getAPIInterface().resendOTP(url, etPhone.getText().toString());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ResendOTPModel>() {
            @Override
            public void onResponse(int code, ResendOTPModel response, Headers headers) {
                load.dismissLoading();
                if (response.response.responseval) {
                    Toast.makeText(SigninActivity.this, "OTP send" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SigninActivity.this, ""+response.response.reason , Toast.LENGTH_SHORT).show();
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

    private void callApiOtpVerify(String otp,final LoginModel responseTemp) {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/ValidateOTP";

        Log.e("URl", "*** " + url);
        Call<VerifyRegisterOTPModel> call = RetrofitClient.getAPIInterface().verifyRegisterOTP(url, etPhone.getText().toString(),otp);
        Request request = new RetrofitRequest<>(call, new ResponseListener<VerifyRegisterOTPModel>() {
            @Override
            public void onResponse(int code, VerifyRegisterOTPModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().isResponseval()) {
                    dialog.dismiss();
                    new PreferenceManager(SigninActivity.this).setLoginModel(responseTemp);
                    new PreferenceManager(SigninActivity.this).setAutoLogin(true);
                    startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                    finish();

                }else{
                    Toast.makeText(SigninActivity.this, ""+response.response.reason , Toast.LENGTH_SHORT).show();
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

    private boolean validate() {

        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            etPhone.setError("Please Enter Mobile Number");
            return false;
        }
        return true;
    }
}
