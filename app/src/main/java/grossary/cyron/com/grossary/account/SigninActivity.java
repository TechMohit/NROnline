package grossary.cyron.com.grossary.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import grossary.cyron.com.grossary.home.HomeActivity;
import grossary.cyron.com.grossary.R;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        startActivity(new Intent(SigninActivity.this, HomeActivity.class));

    }
}
