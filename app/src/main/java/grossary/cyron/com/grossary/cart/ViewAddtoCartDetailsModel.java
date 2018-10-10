package grossary.cyron.com.grossary.cart;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ViewAddtoCartDetailsModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("objViewAddCartList")
    private List<ObjviewaddcartlistEntity> objviewaddcartlist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public List<ObjviewaddcartlistEntity> getObjviewaddcartlist() {
        return objviewaddcartlist;
    }

    public void setObjviewaddcartlist(List<ObjviewaddcartlistEntity> objviewaddcartlist) {
        this.objviewaddcartlist = objviewaddcartlist;
    }

    public  class ResponseEntity {
        @SerializedName("Reason")
        private String reason;
        @SerializedName("ResponseVal")
        private boolean responseval;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public boolean getResponseval() {
            return responseval;
        }

        public void setResponseval(boolean responseval) {
            this.responseval = responseval;
        }
    }

    public  class ObjviewaddcartlistEntity {
        @SerializedName("ShippingCharges")
        private int shippingcharges;
        @SerializedName("TotalSellingPrice")
        private int totalsellingprice;
        @SerializedName("OrderId")
        private String orderid;
        @SerializedName("UnitQty")
        private int unitqty;
        @SerializedName("MRPPrice")
        private int mrpprice;
        @SerializedName("SellingPrice")
        private int sellingprice;
        @SerializedName("Qty")
        private String qty;
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("ProductName")
        private String productname;

        public int getShippingcharges() {
            return shippingcharges;
        }

        public void setShippingcharges(int shippingcharges) {
            this.shippingcharges = shippingcharges;
        }

        public int getTotalsellingprice() {
            return totalsellingprice;
        }

        public void setTotalsellingprice(int totalsellingprice) {
            this.totalsellingprice = totalsellingprice;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getUnitqty() {
            return unitqty;
        }

        public void setUnitqty(int unitqty) {
            this.unitqty = unitqty;
        }

        public int getMrpprice() {
            return mrpprice;
        }

        public void setMrpprice(int mrpprice) {
            this.mrpprice = mrpprice;
        }

        public int getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(int sellingprice) {
            this.sellingprice = sellingprice;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
    }
}
