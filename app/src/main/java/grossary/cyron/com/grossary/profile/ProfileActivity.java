package grossary.cyron.com.grossary.profile;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import grossary.cyron.com.grossary.R;

public class ProfileActivity extends AppCompatActivity {

    private Switch switchBtn;
    private TextInputLayout nameLay,eEmailLay,addLay,gstLay;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();

        switchBtn.setChecked(true);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    nameLay.setEnabled(isChecked);
                    eEmailLay.setEnabled(isChecked);
                    addLay.setEnabled(isChecked);
                    gstLay.setEnabled(isChecked);
            }
        });
    }

    private void initView() {
        switchBtn=findViewById(R.id.switchBtn);
        nameLay=findViewById(R.id.nameLay);
        eEmailLay=findViewById(R.id.eEmailLay);
        addLay=findViewById(R.id.addLay);
        gstLay=findViewById(R.id.gstLay);
    }
}
