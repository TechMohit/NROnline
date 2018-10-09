package grossary.cyron.com.grossary.utility.retrofit;

import java.util.List;

import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.account.RegisterModel;
import grossary.cyron.com.grossary.account.ResendOTPModel;
import grossary.cyron.com.grossary.account.VerifyRegisterOTPModel;
import grossary.cyron.com.grossary.home.HomeModel;
import grossary.cyron.com.grossary.profile.GetUserProfileModel;
import grossary.cyron.com.grossary.profile.GetUserProfileUpdateModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface APIInterface {

    @POST()
    @FormUrlEncoded
    Call<LoginModel> authenticate(@Url String url,@Field("MobileNumber") String MobileNumber);

    @POST()
    @FormUrlEncoded
    Call<GetUserProfileUpdateModel> getUserProfileUpdate(@Url String url, @Field("userId") String UserId, @Field("LoginId") String LoginId
    ,@Field("FullName") String FullName, @Field("MobileNo") String MobileNo,@Field("Email") String Email, @Field("Address") String Address
    ,@Field("GSTNumber") String GSTNumber);

    @POST()
    @FormUrlEncoded
    Call<GetUserProfileModel> getUserProfile(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<ResendOTPModel> resendOTP(@Url String url, @Field("MobileNumber") String MobileNumber);

    @POST()
    @FormUrlEncoded
    Call<VerifyRegisterOTPModel> verifyRegisterOTP(@Url String url, @Field("MobileNumber") String MobileNumber, @Field("OTP") String otp);

    @POST()
    @FormUrlEncoded
    Call<RegisterModel> register(@Url String url, @Field("PortalLoginName") String PortalLoginName, @Field("Password") String Password,
                                 @Field("Address") String Address, @Field("MobileNumber") String MobileNumber, @Field("EmailId") String EmailId,
                                 @Field("GSTNumber") String GSTNumber);

    @POST()
    @FormUrlEncoded
    Call<HomeModel> homeDetailsAPI(@Url String url, @Field("MobileNumber") String MobileNumber);

    interface Header {
        String AUTHORIZATION = "Authorization";
        String CONTENT_TYPE = "Content-Type";
    }

}
