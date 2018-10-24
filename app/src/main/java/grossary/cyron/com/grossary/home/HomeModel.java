package grossary.cyron.com.grossary.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModel {


    @SerializedName("Response")
    private ResponseEntity Response;
    @SerializedName("Status")
    private String Status;
    @SerializedName("objTotalCartItemCount")
    private ObjTotalCartItemCountEntity objTotalCartItemCount;
    @SerializedName("objOfferProdList")
    private List<ObjOfferProdListEntity> objOfferProdList;
    @SerializedName("objOfferImageList")
    private List<ObjOfferImageListEntity> objOfferImageList;
    @SerializedName("objOfferDetailsList")
    private List<ObjOfferDetailsListEntity> objOfferDetailsList;
    @SerializedName("objStoreDetailsList")
    private List<ObjStoreDetailsListEntity> objStoreDetailsList;
    @SerializedName("objCategoryList")
    private List<ObjCategoryListEntity> objCategoryList;

    public ResponseEntity getResponse() {
        return Response;
    }

    public void setResponse(ResponseEntity Response) {
        this.Response = Response;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public ObjTotalCartItemCountEntity getObjTotalCartItemCount() {
        return objTotalCartItemCount;
    }

    public void setObjTotalCartItemCount(ObjTotalCartItemCountEntity objTotalCartItemCount) {
        this.objTotalCartItemCount = objTotalCartItemCount;
    }

    public List<ObjOfferProdListEntity> getObjOfferProdList() {
        return objOfferProdList;
    }

    public void setObjOfferProdList(List<ObjOfferProdListEntity> objOfferProdList) {
        this.objOfferProdList = objOfferProdList;
    }

    public List<ObjOfferImageListEntity> getObjOfferImageList() {
        return objOfferImageList;
    }

    public void setObjOfferImageList(List<ObjOfferImageListEntity> objOfferImageList) {
        this.objOfferImageList = objOfferImageList;
    }

    public List<HomeModel.ObjOfferDetailsListEntity> getObjOfferDetailsList() {
        return objOfferDetailsList;
    }

    public void setObjOfferDetailsList(List<ObjOfferDetailsListEntity> objOfferDetailsList) {
        this.objOfferDetailsList = objOfferDetailsList;
    }

    public List<ObjStoreDetailsListEntity> getObjStoreDetailsList() {
        return objStoreDetailsList;
    }

    public void setObjStoreDetailsList(List<ObjStoreDetailsListEntity> objStoreDetailsList) {
        this.objStoreDetailsList = objStoreDetailsList;
    }

    public List<ObjCategoryListEntity> getObjCategoryList() {
        return objCategoryList;
    }

    public void setObjCategoryList(List<ObjCategoryListEntity> objCategoryList) {
        this.objCategoryList = objCategoryList;
    }

    public static class ResponseEntity {
        @SerializedName("Reason")
        private String Reason;
        @SerializedName("ResponseVal")
        private boolean ResponseVal;

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public boolean getResponseVal() {
            return ResponseVal;
        }

        public void setResponseVal(boolean ResponseVal) {
            this.ResponseVal = ResponseVal;
        }
    }

    public static class ObjTotalCartItemCountEntity {
        @SerializedName("TotalItemCount")
        private int TotalItemCount;

        public int getTotalItemCount() {
            return TotalItemCount;
        }

        public void setTotalItemCount(int TotalItemCount) {
            this.TotalItemCount = TotalItemCount;
        }
    }

    public static class ObjOfferProdListEntity {
        @SerializedName("ShippingCharge")
        private String ShippingCharge;
        @SerializedName("SubProductDesc")
        private String SubProductDesc;
        @SerializedName("SubProductQTY")
        private String SubProductQTY;
        @SerializedName("SellingPrice")
        private String SellingPrice;
        @SerializedName("MRPPrice")
        private String MRPPrice;
        @SerializedName("StoreName")
        private String StoreName;
        @SerializedName("ProductImage")
        private String ProductImage;
        @SerializedName("ProductName")
        private String ProductName;
        @SerializedName("ProductDescId")
        private int ProductDescId;
        @SerializedName("StoreId")
        private int StoreId;
        @SerializedName("ProductId")
        private int ProductId;

        public String getShippingCharge() {
            return ShippingCharge;
        }

        public void setShippingCharge(String ShippingCharge) {
            this.ShippingCharge = ShippingCharge;
        }

        public String getSubProductDesc() {
            return SubProductDesc;
        }

        public void setSubProductDesc(String SubProductDesc) {
            this.SubProductDesc = SubProductDesc;
        }

        public String getSubProductQTY() {
            return SubProductQTY;
        }

        public void setSubProductQTY(String SubProductQTY) {
            this.SubProductQTY = SubProductQTY;
        }

        public String getSellingPrice() {
            return SellingPrice;
        }

        public void setSellingPrice(String SellingPrice) {
            this.SellingPrice = SellingPrice;
        }

        public String getMRPPrice() {
            return MRPPrice;
        }

        public void setMRPPrice(String MRPPrice) {
            this.MRPPrice = MRPPrice;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getProductDescId() {
            return ProductDescId;
        }

        public void setProductDescId(int ProductDescId) {
            this.ProductDescId = ProductDescId;
        }

        public int getStoreId() {
            return StoreId;
        }

        public void setStoreId(int StoreId) {
            this.StoreId = StoreId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }
    }

    public static class ObjOfferImageListEntity {
        @SerializedName("OfferImage")
        private String OfferImage;
        @SerializedName("OfferId")
        private int OfferId;

        public String getOfferImage() {
            return OfferImage;
        }

        public void setOfferImage(String OfferImage) {
            this.OfferImage = OfferImage;
        }

        public int getOfferId() {
            return OfferId;
        }

        public void setOfferId(int OfferId) {
            this.OfferId = OfferId;
        }
    }

    public static class ObjOfferDetailsListEntity {
        @SerializedName("ProductImage")
        private String ProductImage;
        @SerializedName("SellingPrice")
        private String SellingPrice;
        @SerializedName("MRPPrice")
        private String MRPPrice;
        @SerializedName("ProductName")
        private String ProductName;
        @SerializedName("ProductId")
        private int ProductId;
        @SerializedName("ProductDescId")
        private int ProductDescId;

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String ProductImage) {
            this.ProductImage = ProductImage;
        }

        public String getSellingPrice() {
            return SellingPrice;
        }

        public void setSellingPrice(String SellingPrice) {
            this.SellingPrice = SellingPrice;
        }

        public String getMRPPrice() {
            return MRPPrice;
        }

        public void setMRPPrice(String MRPPrice) {
            this.MRPPrice = MRPPrice;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getProductDescId() {
            return ProductDescId;
        }

        public void setProductDescId(int ProductDescId) {
            this.ProductDescId = ProductDescId;
        }
    }

    public static class ObjStoreDetailsListEntity {
        @SerializedName("StoreImage")
        private String StoreImage;
        @SerializedName("StoreName")
        private String StoreName;
        @SerializedName("StoreId")
        private int StoreId;

        public String getStoreImage() {
            return StoreImage;
        }

        public void setStoreImage(String StoreImage) {
            this.StoreImage = StoreImage;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public int getStoreId() {
            return StoreId;
        }

        public void setStoreId(int StoreId) {
            this.StoreId = StoreId;
        }
    }

    public static class ObjCategoryListEntity {
        @SerializedName("CatergoryImage")
        private String CatergoryImage;
        @SerializedName("CatergoryName")
        private String CatergoryName;
        @SerializedName("CatergoryId")
        private int CatergoryId;

        public String getCatergoryImage() {
            return CatergoryImage;
        }

        public void setCatergoryImage(String CatergoryImage) {
            this.CatergoryImage = CatergoryImage;
        }

        public String getCatergoryName() {
            return CatergoryName;
        }

        public void setCatergoryName(String CatergoryName) {
            this.CatergoryName = CatergoryName;
        }

        public int getCatergoryId() {
            return CatergoryId;
        }

        public void setCatergoryId(int CatergoryId) {
            this.CatergoryId = CatergoryId;
        }
    }
}
