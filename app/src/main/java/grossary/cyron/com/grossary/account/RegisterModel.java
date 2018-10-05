package grossary.cyron.com.grossary.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class RegisterModel {

    
    @SerializedName("Response")
    private ResponseEntity response;
    
    @SerializedName("OTP")
    private String otp;
    
    @SerializedName("MobileNumber")
    private String mobilenumber;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public static class ResponseEntity {
        
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
}
