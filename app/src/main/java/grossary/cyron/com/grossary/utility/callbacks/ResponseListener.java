package grossary.cyron.com.grossary.utility.callbacks;


import okhttp3.Headers;

public interface ResponseListener<T> {

    void onResponse(T response, Headers headers);

    void onError(String error);

    void onFailure(Throwable throwable);
}
