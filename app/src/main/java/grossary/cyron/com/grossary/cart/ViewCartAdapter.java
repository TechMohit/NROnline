package grossary.cyron.com.grossary.cart;

import android.app.Activity;
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
import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.utility.GlideApp;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;


public class ViewCartAdapter extends RecyclerView.Adapter {

    private List<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity> clickListener;

    public ViewCartAdapter(Activity activity, OnItemClickListener<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).title.setText(String.format("%s", object.getProductname()));

        GlideApp.with(activity)
                .load(object.getProductimage())
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
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<ViewAddtoCartDetailsModel.ObjviewaddcartlistEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private CardView card_parent;
        private ImageView imgView;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tvProductName);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
        }
    }

}