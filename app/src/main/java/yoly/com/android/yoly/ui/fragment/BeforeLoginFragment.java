package yoly.com.android.yoly.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.CountryChooseActivity;
import yoly.com.android.yoly.ui.activity.LoginActivity;

public class BeforeLoginFragment extends Fragment {
    public BeforeLoginFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_before_login, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_register)
    public void showRegisterActivity() {
        startActivity(new Intent(getContext(), CountryChooseActivity.class));
    }

    @OnClick(R.id.btn_login)
    public void showLoginActivity() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}