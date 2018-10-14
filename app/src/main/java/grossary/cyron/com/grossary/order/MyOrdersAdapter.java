package grossary.cyron.com.grossary.order;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import grossary.cyron.com.grossary.R;
import grossary.cyron.com.grossary.category.CategoryModel;
import grossary.cyron.com.grossary.utility.GlideApp;
import grossary.cyron.com.grossary.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ADD;
import static grossary.cyron.com.grossary.utility.Constant.CATEGORY.ONCLICK;


public class MyOrdersAdapter extends RecyclerView.Adapter {

    private List<ViewOrderListModel.OrderlistEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<ViewOrderListModel.OrderlistEntity> clickListener;

    public MyOrdersAdapter(Activity activity, OnItemClickListener<ViewOrderListModel.OrderlistEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final ViewOrderListModel.OrderlistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvOrderId.setText("Order ID: "+object.getTranno());
        if(object.getDeliverycharges().equalsIgnoreCase("0.00")){
            ((ImageTypeViewHolder) holder).tvDeliveryCharge.setText("Free");

        }else {
            ((ImageTypeViewHolder) holder).tvDeliveryCharge.setText("₹" + object.getDeliverycharges());
        }
        ((ImageTypeViewHolder) holder).tvDatePlaced.setText("Place on "+object.getTrandate());
        ((ImageTypeViewHolder) holder).tvAddress.setText(""+object.getShippingaddress());
        ((ImageTypeViewHolder) holder).tvCharge.setText("₹"+ object.getTotalamount());
        ((ImageTypeViewHolder) holder).tvAmount.setText("₹"+ object.getTotalamount());

//        ((ImageTypeViewHolder) holder).tvMrpPrice.setPaintFlags(((ImageTypeViewHolder) holder).tvMrpPrice.getPaintFlags()
//                | Paint.STRIKE_THRU_TEXT_FLAG);
//
//
        ((ImageTypeViewHolder) holder).btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).btnDetails, listPosition,ONCLICK);
            }
        });

//        ((ImageTypeViewHolder) holder).btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition,ADD);
//            }
//        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<ViewOrderListModel.OrderlistEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAmount,tvOrderId,tvDeliveryCharge,tvDatePlaced,tvAddress,tvCharge;
        private Button btnDetails;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvAmount = itemView.findViewById(R.id.tvAmount);
            this.tvOrderId=itemView.findViewById(R.id.tvOrderId);
            this.tvDeliveryCharge=itemView.findViewById(R.id.tvDeliveryCharge);
            this.tvDatePlaced=itemView.findViewById(R.id.tvDatePlaced);
            this.tvAddress=itemView.findViewById(R.id.tvAddress);
            this.tvCharge=itemView.findViewById(R.id.tvCharge);
            this.btnDetails=itemView.findViewById(R.id.btnDetails);
        }
    }

}