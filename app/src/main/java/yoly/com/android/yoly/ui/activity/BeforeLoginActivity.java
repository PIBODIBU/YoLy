package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;

public class BeforeLoginActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register)
    public void register(View view) {
        startActivity(new Intent(BeforeLoginActivity.this, RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        startActivity(new Intent(BeforeLoginActivity.this, LoginActivity.class));
        finish();
    }
}
