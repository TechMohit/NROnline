package grossary.cyron.com.grossary.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import static android.support.v4.util.Preconditions.checkNotNull;


public class PreferenceManager {

    private final static String FORTIS_PREFERENCES = "cyron_glossary";
    private static final String CITY = "city";

    private SharedPreferences sharedPreferences;

    private PreferenceManager() {

    }

    public PreferenceManager(Context context) {
        this();
        sharedPreferences = context.getApplicationContext().getSharedPreferences(FORTIS_PREFERENCES, Context.MODE_PRIVATE);
    }


    public void setCity(@NonNull String city) {
        sharedPreferences.edit().putString(CITY, city).apply();
    }

    /**
     */
    @Nullable
    public String getCity() {
        String city = sharedPreferences.getString(CITY, null);
        return city;
    }


}
