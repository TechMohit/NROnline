package grossary.cyron.com.grossary.category;

import android.app.FragmentManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.FragmentHelper;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;
import static grossary.cyron.com.grossary.utility.Constant.KEY_NAME.FRAG_PARAMETER;

public class CategoryActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        selectFrag(LIST,getIntent().getStringExtra(ACT_HOME_PARAMETER));
        getFragmentManager().addOnBackStackChangedListener(this);

    }

    public void selectFrag(String tag,String response) {
        Fragment fragment = null;
        Bundle arguments=null;
        switch (tag) {
            case LIST:
                fragment = new CategoryListFragment();
                 arguments = new Bundle();
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                break;
            case LIST_DETAILS:
                fragment = new CategoryListDetailsFragment();
                 arguments = new Bundle();
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;

        }
    }

    @Override
    public void onBackStackChanged() {

    }
}
