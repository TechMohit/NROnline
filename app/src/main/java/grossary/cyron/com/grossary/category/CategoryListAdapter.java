package grossary.cyron.com.grossary.category;

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

import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.offers.OffersListAdapter;
import grossary.cyron.com.grossary.utility.GlideApp;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;


public class CategoryListAdapter extends RecyclerView.Adapter {

    private List<CategoryModel.Projectlist> dataSet;
    private Activity activity;
    private OnItemClickListener<CategoryModel.Projectlist> clickListener;

    public CategoryListAdapter(Activity activity, OnItemClickListener<CategoryModel.Projectlist> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final CategoryModel.Projectlist object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", object.productName));
        ((ImageTypeViewHolder) holder).tvDesc.setText(String.format("%s", object.subProductQTY));
        ((ImageTypeViewHolder) holder).tvSellingPrice.setText("₹"+String.format("%s", object.sellingPrice));
        ((ImageTypeViewHolder) holder).tvMrpPrice.setText(String.format("%s", "(₹"+object.mRPPrice+")"));
        ((ImageTypeViewHolder) holder).tvMrpPrice.setPaintFlags(((ImageTypeViewHolder) holder).tvMrpPrice.getPaintFlags()
                | Paint.STRIKE_THRU_TEXT_FLAG);

        GlideApp.with(activity)
                .load(object.productImage)
                .centerInside()
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<CategoryModel.Projectlist> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName,tvDesc,tvSellingPrice,tvMrpPrice;
        private CardView card_parent;
        private ImageView imgView;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.tvDesc = itemView.findViewById(R.id.tvDesc);
            this.tvSellingPrice = itemView.findViewById(R.id.tvSellingPrice);
            this.tvMrpPrice = itemView.findViewById(R.id.tvMrpPrice);
        }
    }

}