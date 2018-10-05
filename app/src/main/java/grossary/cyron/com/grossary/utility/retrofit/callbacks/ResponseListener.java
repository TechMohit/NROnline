package grossary.cyron.com.grossary.utility.retrofit.callbacks;

import okhttp3.Headers;


public interface ResponseListener<T> {

    void onResponse(int code, T response, Headers headers);

    void onError(int error);

    void onFailure(Throwable throwable);
}
