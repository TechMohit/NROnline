package grossary.cyron.com.grossary.account;

public class LoginModel {

    private float UserId;
    private String LoginId;
    private String FullName;
    private String Mobile;
    private String Email;
    private String Address;
    private String City;
    private String State;
    private String ZipCode;
    private String StoreId;
    private String GstNumber;
    private String RoleCode;
    private String OTP;
    private String Status;
    private String StatusMessage = null;
    private Response Response;


    // Getter Methods

    public float getUserId() {
        return UserId;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getFullName() {
        return FullName;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public String getStoreId() {
        return StoreId;
    }

    public String getGstNumber() {
        return GstNumber;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public String getOTP() {
        return OTP;
    }

    public String getStatus() {
        return Status;
    }

    public String getStatusMessage() {
        return StatusMessage;
    }

    public Response getResponse() {
        return Response;
    }

    // Setter Methods

    public void setUserId(float UserId) {
        this.UserId = UserId;
    }

    public void setLoginId(String LoginId) {
        this.LoginId = LoginId;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }

    public void setStoreId(String StoreId) {
        this.StoreId = StoreId;
    }

    public void setGstNumber(String GstNumber) {
        this.GstNumber = GstNumber;
    }

    public void setRoleCode(String RoleCode) {
        this.RoleCode = RoleCode;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setStatusMessage(String StatusMessage) {
        this.StatusMessage = StatusMessage;
    }

    public void setResponse(Response Response) {
        this.Response = Response;
    }
}

class Response {
    private boolean ResponseVal;
    private String Reason = null;


    // Getter Methods

    public boolean getResponseVal() {
        return ResponseVal;
    }

    public String getReason() {
        return Reason;
    }

    // Setter Methods

    public void setResponseVal(boolean ResponseVal) {
        this.ResponseVal = ResponseVal;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
}