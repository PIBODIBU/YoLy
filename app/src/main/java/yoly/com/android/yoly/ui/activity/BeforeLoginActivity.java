package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.presenter.BeforeLoginPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.BeforeLoginPresenterImpl;
import yoly.com.android.yoly.ui.view.BeforeLoginView;

public class BeforeLoginActivity extends AppCompatActivity implements BeforeLoginView {
    private final String TAG = getClass().getSimpleName();

    private BeforeLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        ButterKnife.bind(this);
        presenter = new BeforeLoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    @OnClick(R.id.btn_register)
    public void showRegisterActivity() {
        startActivity(new Intent(BeforeLoginActivity.this, RegisterActivity.class));
        finish();
    }

    @Override
    @OnClick(R.id.btn_login)
    public void showLoginActivity() {
        startActivity(new Intent(BeforeLoginActivity.this, LoginActivity.class));
        finish();
    }
}