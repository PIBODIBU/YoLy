package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.Values;
import yoly.com.android.yoly.ui.adapter.BeforeLoginPagerAdapter;
import yoly.com.android.yoly.ui.fragment.BeforeLoginFragment;
import yoly.com.android.yoly.ui.fragment.LearnFragment;
import yoly.com.android.yoly.ui.view.BeforeLoginView;

public class BeforeLoginActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BeforeLoginView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @BindView(R.id.page_indicator)
    public CirclePageIndicator pageIndicator;

    private BeforeLoginPagerAdapter adapter;
    private LinkedList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        ButterKnife.bind(this);

        fragments = new LinkedList<>();

        fragments.add(new LearnFragment()
                .setDescription(getResources().getString(R.string.slide_title_1))
                .setImageRes(R.drawable.slide_learn_3));
        fragments.add(new LearnFragment()
                .setDescription(getResources().getString(R.string.slide_title_2))
                .setImageRes(R.drawable.slide_learn_3));
        fragments.add(new LearnFragment()
                .setDescription(getResources().getString(R.string.slide_title_3))
                .setImageRes(R.drawable.slide_learn_3));
        fragments.add(new LearnFragment()
                .setDescription(getResources().getString(R.string.slide_title_4))
                .setImageRes(R.drawable.slide_learn_3));
        fragments.add(new BeforeLoginFragment()
                .setView(this));

        adapter = new BeforeLoginPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        pageIndicator.setViewPager(viewPager);
        pageIndicator.setFillColor(ContextCompat.getColor(this, R.color.colorPrimary));
        pageIndicator.setRadius(Values.PAGE_INDICATOR_RADIUS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void redirectToMainPage() {
        startActivity(new Intent(BeforeLoginActivity.this, NewsListActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization

                redirectToMainPage();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (fragments == null)
            return;

        pageIndicator.setVisibility(position == fragments.size() - 1 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void socialLoginVK() {
        VKSdk.login(this, "email", "status");
    }

    @Override
    public void socialLoginFB() {

    }

    @Override
    public void socialLoginTW() {

    }
}