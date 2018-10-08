package grossary.cyron.com.grossary.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    new PreferenceManager(SigninActivity.this).setLoginModel(response);
                    new PreferenceManager(SigninActivity.this).setAutoLogin(true);
                    startActivity(new Intent(SigninActivity.this, HomeActivity.class));
                    finish();
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

    private boolean validate() {

        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            etPhone.setError("Please Enter Mobile Number");
            return false;
        }
        return true;
    }
}
