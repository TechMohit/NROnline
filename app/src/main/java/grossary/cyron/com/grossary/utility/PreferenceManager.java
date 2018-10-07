package grossary.cyron.com.grossary.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import grossary.cyron.com.grossary.account.LoginModel;

import static android.support.v4.util.Preconditions.checkNotNull;


public class PreferenceManager {

    private final static String FORTIS_PREFERENCES = "cyron_glossary";
    private static final String CITY = "city";
    private static final String LOGIN_MODEL = "login_model";
    private static final String AUTO_LOGIN = "auto_login";



    private SharedPreferences sharedPreferences;

    private PreferenceManager() {

    }

    public PreferenceManager(Context context) {
        this();
        sharedPreferences = context.getApplicationContext().getSharedPreferences(FORTIS_PREFERENCES, Context.MODE_PRIVATE);
    }


    public void setCity( String city) {
        sharedPreferences.edit().putString(CITY, city).apply();
    }
    public String getCity() {
        String city = sharedPreferences.getString(CITY, null);
        return city;
    }
    public void setLoginModel( LoginModel loginModel) {
        sharedPreferences.edit().putString(LOGIN_MODEL, new Gson().toJson(loginModel)).apply();
    }
    public LoginModel getLoginModel() {
        String loginModel = sharedPreferences.getString(LOGIN_MODEL, null);
        return new Gson().fromJson(loginModel,LoginModel.class);
    }
    public void setAutoLogin( boolean login) {
        sharedPreferences.edit().putBoolean(AUTO_LOGIN, login).apply();
    }
    public boolean getAutoLogin() {
        boolean loginModel = sharedPreferences.getBoolean(AUTO_LOGIN, false);
        return loginModel;
    }


}
