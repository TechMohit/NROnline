package grossary.cyron.com.grossary.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.Constant;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

public class DrawerFragment extends Fragment implements OnItemClickListener {

    private DrawerAdapter adapter = null;
    private DrawerListener drawerListener;
    private RecyclerView recyclerView;

    public DrawerFragment() {
        // Required empty public constructor
    }

    public interface DrawerListener {
        void drawerOnItemClicked(String tag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drawerListener = (DrawerListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_drawer, container, false);
        initView(view);
        setValue();

        return view;
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);
    }

    private void setValue() {
        List<DrawerItem> newsList = new ArrayList<>();

        DrawerItem drawerItem = new DrawerItem();
        drawerItem.setText("Home");
        drawerItem.setTag("HOME");
        drawerItem.setIcon(R.drawable.home_pink);
        drawerItem.setIconUnSelect(R.drawable.home_white);
        newsList.add(drawerItem);

        DrawerItem drawerItem1 = new DrawerItem();
        drawerItem1.setText("My Profile");
        drawerItem1.setTag(Constant.NAV_DRAWER.MY_PROFILE);
        drawerItem1.setIcon(R.drawable.user_pink);
        drawerItem1.setIconUnSelect(R.drawable.user);
        newsList.add(drawerItem1);

        DrawerItem drawerItem2 = new DrawerItem();
        drawerItem2.setText("My Orders");
        drawerItem2.setTag(Constant.NAV_DRAWER.MY_ORDER);
        drawerItem2.setIcon(R.drawable.online_order_pink);
        drawerItem2.setIconUnSelect(R.drawable.online_order);
        newsList.add(drawerItem2);

        DrawerItem drawerItem3 = new DrawerItem();
        drawerItem3.setText("Terms & Condition");
        drawerItem3.setTag("TERMS_&_CONDITION");
        drawerItem3.setIcon(R.drawable.terms_pink);
        drawerItem3.setIconUnSelect(R.drawable.terms);
        newsList.add(drawerItem3);

        DrawerItem drawerItem4 = new DrawerItem();
        drawerItem4.setText("Privacy Policy");
        drawerItem4.setTag("PRIVACY_POLICY");
        drawerItem4.setIcon(R.drawable.keyhole_pink);
        drawerItem4.setIconUnSelect(R.drawable.keyhole);
        newsList.add(drawerItem4);

        DrawerItem drawerItem5 = new DrawerItem();
        drawerItem5.setText("About Us");
        drawerItem5.setTag("ABOUT_US");
        drawerItem5.setIcon(R.drawable.online_shop_pink);
        drawerItem5.setIconUnSelect(R.drawable.online_shop);
        newsList.add(drawerItem5);

        DrawerItem drawerItem6 = new DrawerItem();
        drawerItem6.setText("Log Out");
        drawerItem6.setTag(Constant.NAV_DRAWER.LOG_OUT);
        drawerItem6.setIcon(R.drawable.logout);
        drawerItem6.setIconUnSelect(R.drawable.logout_white);
        newsList.add(drawerItem6);
        
        adapter = new DrawerAdapter(getActivity(), newsList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Object o, View view, int position,String type) {
        drawerListener.drawerOnItemClicked(((DrawerItem) o).getTag());
        adapter.selectedPosition(position);
    }

    public void setHighlitedPosition(int position) {
        if (adapter != null)
            adapter.selectedPosition(position);
    }

    public void focus() {
        if (recyclerView != null && adapter !=null)
            recyclerView.smoothScrollToPosition(adapter.selectedPosition);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
