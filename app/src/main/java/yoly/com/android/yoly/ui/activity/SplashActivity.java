package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.SharedPrefHelper;
import yoly.com.android.yoly.helper.Values;
import yoly.com.android.yoly.ui.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    private SharedPrefHelper sharedPrefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        sharedPrefHelper = new SharedPrefHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,
//                        sharedPrefHelper.isFirstLaunch() ? LearnActivity.class : BeforeLoginActivity.class);
                        NewLookActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, Values.SPLASH_DISPLAY_LENGTH);
    }
}
