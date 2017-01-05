package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;

public class LookEditActivity extends BaseNavigationDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_look_edit);
    }
}