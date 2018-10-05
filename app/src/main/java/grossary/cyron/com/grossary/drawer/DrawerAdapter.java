package grossary.cyron.com.grossary.drawer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;


/**
 * Created by Satyam Kumar Naik on 1/11/2018.
 */

public class DrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<DrawerItem> list;
    private final OnItemClickListener onItemClickListener;
    public int selectedPosition = 0;

    public DrawerAdapter(Context context, List<DrawerItem> list, OnItemClickListener onItemClickListener) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_drawer_item, parent, false);
        return new ImageTypeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DrawerItem object = list.get(position);
//        ((ImageTypeViewHolder) holder).linItem.setText("" + object);
        ((ImageTypeViewHolder) holder).linItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    onItemClickListener.onItemClick(object, view,position);
            }
        });
    }

    public void selectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

       private LinearLayout linItem;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.linItem = itemView.findViewById(R.id.lin_item);
        }
    }

}