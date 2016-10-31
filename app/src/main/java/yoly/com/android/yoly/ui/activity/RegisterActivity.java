package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.IntentKeys;
import yoly.com.android.yoly.ui.presenter.RegisterPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.RegisterPresenterImpl;
import yoly.com.android.yoly.ui.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private RegisterPresenter presenter;

    @BindView(R.id.et_username)
    public AppCompatEditText ETUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(new Intent(RegisterActivity.this, AfterRegisterActivity.class)
                .putExtra(IntentKeys.NAME, ETUsername.getText().toString()));
    }
}