package grossary.cyron.com.grossary.account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.LoadingView;
import okhttp3.Headers;
import okhttp3.internal.Util;
import retrofit2.Call;

import static grossary.cyron.com.grossary.utility.Constant.URL.BASE_URL;

public class SignupActivity extends AppCompatActivity {


    private Button btn_register;
    private EditText etUserName, etPassword, etMobile, etEmail,etAddress,etGst;
    private LoadingView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etAddress=findViewById(R.id.etAddress);
        etGst=findViewById(R.id.etGst);

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    Toast.makeText(SignupActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    callApiRegister();
                }
            }
        });
//        startActivity(new Intent(SigninActivity.this, HomeActivity.class));

    }

    private void callApiRegister() {

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
        }else if (TextUtils.isEmpty(etAddress.getText().toString())) {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();
            etAddress.setError("Please Enter Address");
            return false;
        }else if (TextUtils.isEmpty(etGst.getText().toString())) {
            Toast.makeText(this, "Please Enter GST Number", Toast.LENGTH_SHORT).show();
            etGst.setError("Please Enter GST Number");
            return false;
        }
        return true;
    }
}
