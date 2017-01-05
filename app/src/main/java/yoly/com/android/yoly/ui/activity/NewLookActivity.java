package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.fragment.NewLookPhotoUploadFragment;
import yoly.com.android.yoly.ui.fragment.NewLookWorkspaceFragment;
import yoly.com.android.yoly.ui.view.NewLookView;

public class NewLookActivity extends BaseNavigationDrawerActivity implements NewLookView {
    @BindView(R.id.frame)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_photo_upload);

        ButterKnife.bind(this);
        getDrawer();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, new NewLookPhotoUploadFragment()
                        .setView(this))
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPhotoUploaded(Drawable photo) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new NewLookWorkspaceFragment()
                        .setView(this)
                        .setPhoto(photo))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDismissPressed() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new NewLookPhotoUploadFragment()
                        .setView(this))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(null)
                .commit();
    }
}
