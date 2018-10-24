package grossary.cyron.com.grossary.offers;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.GlideApp;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ONCLICK;


public class OffersListAdapter extends RecyclerView.Adapter {

    private ArrayList<HomeModel.ObjOfferDetailsListEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<HomeModel.ObjOfferDetailsListEntity> clickListener;

    public OffersListAdapter( Activity activity, OnItemClickListener<HomeModel.ObjOfferDetailsListEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final HomeModel.ObjOfferDetailsListEntity object = dataSet.get(listPosition);


        ((ImageTypeViewHolder) holder).tvName.setText(""+object.getProductName());
        ((ImageTypeViewHolder) holder).tvMrpPrice.setText("\u20B9"+object.getMRPPrice());

        ((ImageTypeViewHolder) holder).tvMrpPrice.setPaintFlags(((ImageTypeViewHolder) holder).tvMrpPrice.getPaintFlags()
                | Paint.STRIKE_THRU_TEXT_FLAG);
        ((ImageTypeViewHolder) holder).tvSellerPrice.setText("\u20B9"+object.getSellingPrice());

                GlideApp.with(activity)
                .load(object.getProductImage())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.logo_long)
                .error(R.drawable.ic_launcher_background)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(((ImageTypeViewHolder) holder).imgView);

        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition,ONCLICK);
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
        if(dataSet==null)
            return 0;

        return dataSet.size();
    }

    public void setAdapterData(ArrayList<HomeModel.ObjOfferDetailsListEntity> offersList) {

        dataSet=offersList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private CardView card_parent;
        private ImageView imgView;
        private TextView tvSellerPrice,tvMrpPrice,tvName;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvMrpPrice = itemView.findViewById(R.id.tvMrpPrice);
            this.tvSellerPrice = itemView.findViewById(R.id.tvSellerPrice);
        }
    }

}