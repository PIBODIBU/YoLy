package yoly.com.android.yoly.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.presenter.RegisterPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.RegisterPresenterImpl;
import yoly.com.android.yoly.ui.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private RegisterPresenter presenter;

    @BindView(R.id.tv_birth_date)
    public TextView TVBirthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        presenter = new RegisterPresenterImpl(this);
    }

    @Override
    @OnClick(R.id.ll_birth_date)
    public void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                presenter.getOnDateSetListener(),
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));

        dpd.show(getFragmentManager(), "DatePickerDialog");
    }

    @Override
    public void setDate(String date) {
        TVBirthDate.setText(date);
    }
}