package grossary.cyron.com.grossary.sellers;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;


public class SellersListAdapter extends RecyclerView.Adapter {

    private ArrayList<SellersModel> dataSet;
    private Activity activity;
    private OnItemClickListener<SellersModel> clickListener;

    public SellersListAdapter(ArrayList<SellersModel> data, Activity activity, OnItemClickListener<SellersModel> clickListener) {
        this.dataSet = data;
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final SellersModel object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).title.setText(String.format("%s", object.tittle));
        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sellers, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private CardView card_parent;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.textView);
            this.card_parent = itemView.findViewById(R.id.card_parent);
        }
    }

}