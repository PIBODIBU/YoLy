package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.IntentKeys;

public class AfterRegisterActivity extends AppCompatActivity {

    @BindView(R.id.tv_greets)
    public TextView TVGreets;

    @BindView(R.id.tv_email)
    public TextView TVEmail;

    @BindString(R.string.tv_successfully_registered)
    public String greetMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register);
        ButterKnife.bind(this);

        if (getIntent() == null || getIntent().getExtras() == null) {
            return;
        }

        if (getIntent().getExtras().getString(IntentKeys.NAME).equals("")
//                || getIntent().getExtras().getString(IntentKeys.EMAIL).equals("")
                ) {
            return;
        }

        TVGreets.setText(greetMessage.replace("ИМЯ", getIntent().getExtras().getString(IntentKeys.NAME)));
//        TVEmail.setText(getIntent().getExtras().getString(IntentKeys.EMAIL));
    }
}
