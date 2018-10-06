package grossary.cyron.com.grossary.utility.retrofit;

import java.util.List;

import grossary.cyron.com.grossary.account.LoginModel;
import grossary.cyron.com.grossary.account.RegisterModel;
import grossary.cyron.com.grossary.home.HomeModel;
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
