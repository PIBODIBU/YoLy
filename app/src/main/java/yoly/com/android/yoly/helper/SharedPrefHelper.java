package yoly.com.android.yoly.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private SharedPreferences sharedPreferences;

    public static class Names {
        public static final String DEFAULT = "yoly.com.android.yoly";
    }

    public static class Keys {
        public static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    }

    public SharedPrefHelper(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(Names.DEFAULT, Context.MODE_PRIVATE);
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(Keys.FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(boolean firstLaunch) {
        sharedPreferences.edit().putBoolean(Keys.FIRST_LAUNCH, firstLaunch).apply();
    }
}
