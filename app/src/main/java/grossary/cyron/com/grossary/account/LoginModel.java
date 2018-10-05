package grossary.cyron.com.grossary.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {
    
    @SerializedName("Status")
    private String status;
    
    @SerializedName("OTP")
    private String otp;
    
    @SerializedName("RoleCode")
    private String rolecode;
    
    @SerializedName("GstNumber")
    private String gstnumber;
    
    @SerializedName("StoreId")
    private String storeid;
    
    @SerializedName("ZipCode")
    private String zipcode;
    
    @SerializedName("State")
    private String state;
    
    @SerializedName("City")
    private String city;
    
    @SerializedName("Address")
    private String address;
    
    @SerializedName("Email")
    private String email;
    
    @SerializedName("Mobile")
    private String mobile;
    
    @SerializedName("FullName")
    private String fullname;
    
    @SerializedName("LoginId")
    private String loginid;
    
    @SerializedName("UserId")
    private int userid;
}
