package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;

public class PopularLooksActivity extends BaseNavigationDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_looks);
        ButterKnife.bind(this);
    }
}
