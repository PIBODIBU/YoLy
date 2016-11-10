package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.IntentKeys;

public class AfterRegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_greets)
    public TextView TVGreets;

    @BindView(R.id.tv_email)
    public TextView TVEmail;

    @BindString(R.string.tv_successfully_registered)
    public String greetMessage;

    @BindString(R.string.tv_successfully_registered_filter)
    public String greetMessageFilter;

    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register);
        ButterKnife.bind(this);

        if (getIntent() == null || getIntent().getExtras() == null) {
            finish();
            return;
        }

        if (!getIntent().getExtras().containsKey(IntentKeys.NAME) || getIntent().getExtras().containsKey(IntentKeys.EMAIL)) {
            finish();
            return;
        }

        name = getIntent().getExtras().getString(IntentKeys.NAME);
        email = getIntent().getExtras().getString(IntentKeys.EMAIL);

        TVGreets.setText(greetMessage.replace(greetMessageFilter, name));
        TVEmail.setText(email);
    }

    @OnClick(R.id.btn_continue)
    public void startUsing() {
        startActivity(new Intent(AfterRegisterActivity.this, NewsListActivity.class));
        finish();
    }
}
