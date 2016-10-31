package yoly.com.android.yoly.ui.presenter.implementation;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import yoly.com.android.yoly.ui.presenter.RegisterPresenter;
import yoly.com.android.yoly.ui.view.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter, DatePickerDialog.OnDateSetListener {
    private RegisterView view;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        String date = String.valueOf(dayOfMonth) + "/" + String.valueOf((monthOfYear + 1)) + "/" + String.valueOf(year);
    }

    @Override
    public DatePickerDialog.OnDateSetListener getOnDateSetListener() {
        return this;
    }

    @Override
    public void onDestroy() {

    }
}