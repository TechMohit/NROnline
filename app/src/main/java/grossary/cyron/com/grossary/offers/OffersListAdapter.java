package grossary.cyron.com.grossary.offers;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;


public class OffersListAdapter extends RecyclerView.Adapter {

    private ArrayList<OffersModel> dataSet;
    private Activity activity;
    private OnItemClickListener<OffersModel> clickListener;

    public OffersListAdapter(ArrayList<OffersModel> data, Activity activity, OnItemClickListener<OffersModel> clickListener) {
        this.dataSet = data;
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final OffersModel object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_offers, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private CardView card_parent;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.card_parent = itemView.findViewById(R.id.card_parent);
        }
    }

}