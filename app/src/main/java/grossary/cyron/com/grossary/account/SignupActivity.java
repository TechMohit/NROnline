package grossary.cyron.com.grossary.account;

import android.app.Dialog;
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
import android.widget.Toast;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.LoadingView;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossary.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossary.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;
import static grossary.cyron.com.grossary.utility.Util.validatePassword;

public class SignupActivity extends AppCompatActivity {


    private Button btn_register;
    private EditText etUserName, etPassword, etMobile, etEmail, etAddress, etGst;
    private LoadingView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etGst = findViewById(R.id.etGst);

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (validation()) {
//                    callApiRegister();

                    openDilogOtp();
//                }
            }
        });

    }

    private void openDilogOtp() {
        final Dialog dialog = new Dialog(SignupActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_otp);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.show();

//        Button btn_later = dialog.findViewById(R.id.btn_later);
//        btn_later.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });

//        Button btn_subscribe = dialog.findViewById(R.id.btn_subscribe);
//        btn_subscribe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                ((HomeActivity) context).loadSubscription(HOME_LOCATION);
//            }
//        });
    }

    private void callApiRegister() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/Register";

        Log.e("URl", "*** " + url);
        Call<RegisterModel> call = RetrofitClient.getAPIInterface().register(url, etUserName.getText().toString(), etPassword.getText().toString(),
                etAddress.getText().toString(), etMobile.getText().toString(), etEmail.getText().toString(), etGst.getText().toString());
        Request request = new RetrofitRequest<>(call, new ResponseListener<RegisterModel>() {
            @Override
            public void onResponse(int code, RegisterModel response, Headers headers) {
                load.dismissLoading();
                Toast.makeText(SignupActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                if (response.getResponse().getResponseval()) {

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

    private boolean validation() {

        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
            etUserName.setError("Please Enter Name");
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            etPassword.setError("Please Enter Password");
            return false;
        } else if (!validatePassword(etPassword.getText().toString())) {
            Toast.makeText(this, "Password must contain atleast 1 lower case, 1 upper case, 1 number & 1 special character", Toast.LENGTH_SHORT).show();
            etPassword.setError("Password Pattern mismatch");
            return false;
        } else if (TextUtils.isEmpty(etMobile.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            etMobile.setError("Please Enter Mobile Number");
            return false;
        } else if (etMobile.getText().toString().length() < 10) {
            Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            etMobile.setError("Please Enter Valid Mobile Number");
            return false;
        } else if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
            etEmail.setError("Please Enter Email ID");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            Toast.makeText(this, "Invalid email Id", Toast.LENGTH_SHORT).show();
            etEmail.setError("Please Enter Email ID");
            return false;
        } else if (TextUtils.isEmpty(etAddress.getText().toString())) {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();
            etAddress.setError("Please Enter Address");
            return false;
        } else if (TextUtils.isEmpty(etGst.getText().toString())) {
            Toast.makeText(this, "Please Enter GST Number", Toast.LENGTH_SHORT).show();
            etGst.setError("Please Enter GST Number");
            return false;
        }
        return true;
    }
}
