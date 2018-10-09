package grossary.cyron.com.grossary.category;

import android.app.FragmentManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.FragmentHelper;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.LIST;

public class CategoryActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        selectFrag(LIST);
        getFragmentManager().addOnBackStackChangedListener(this);

    }

    private void selectFrag(String tag) {
        Fragment fragment = null;
        switch (tag) {
            case LIST:
                fragment = new CategoryListFragment();

                break;

        }
        FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);
    }

    @Override
    public void onBackStackChanged() {

    }
}
