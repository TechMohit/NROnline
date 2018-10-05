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
        drawerItem.setIcon(R.drawable.ic_launcher_background);
        newsList.add(drawerItem);
        
        adapter = new DrawerAdapter(getActivity(), newsList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Object o, View view, int position) {
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
