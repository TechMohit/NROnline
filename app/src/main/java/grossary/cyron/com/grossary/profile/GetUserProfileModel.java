package grossary.cyron.com.grossary.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subham_naik on 08-Oct-18.
 */

public class GetUserProfileModel {


    @SerializedName("userId")
    public int userid;
    @SerializedName("LoginId")
    public String loginid;
    @SerializedName("FullName")
    public String fullname;
    @SerializedName("MobileNo")
    public String mobileno;
    @SerializedName("Email")
    public String email;
    @SerializedName("Address")
    public String address;

    public int getUserid() {
        return userid;
    }

    public GetUserProfileModel setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public String getLoginid() {
        return loginid;
    }

    public GetUserProfileModel setLoginid(String loginid) {
        this.loginid = loginid;
        return this;
    }

    public String getFullname() {
        return fullname;
    }

    public GetUserProfileModel setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public String getMobileno() {
        return mobileno;
    }

    public GetUserProfileModel setMobileno(String mobileno) {
        this.mobileno = mobileno;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GetUserProfileModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public GetUserProfileModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getGstnumber() {
        return gstnumber;
    }

    public GetUserProfileModel setGstnumber(String gstnumber) {
        this.gstnumber = gstnumber;
        return this;
    }

    public String getRolecode() {
        return rolecode;
    }

    public GetUserProfileModel setRolecode(String rolecode) {
        this.rolecode = rolecode;
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public GetUserProfileModel setResponse(Response response) {
        this.response = response;
        return this;
    }


    @SerializedName("GSTNumber")
    public String gstnumber;
    @SerializedName("RoleCode")
    public String rolecode;
    @SerializedName("Response")
    public Response response;

    public  class Response {
        @SerializedName("ResponseVal")
        public boolean responseval;

        public boolean isResponseval() {
            return responseval;
        }

        public Response setResponseval(boolean responseval) {
            this.responseval = responseval;
            return this;
        }

        public String getReason() {
            return reason;
        }

        public Response setReason(String reason) {
            this.reason = reason;
            return this;
        }

        @SerializedName("Reason")
        public String reason;
    }
}
