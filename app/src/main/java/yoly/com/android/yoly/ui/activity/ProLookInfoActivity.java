package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;

public class ProLookInfoActivity extends BaseNavigationDrawerActivity {

    @BindView(R.id.background_photo)
    public ImageView IVPhotoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_look_info);
        ButterKnife.bind(this);
        getDrawer();

        Picasso.with(this)
                .load("http://nauguide.esy.es/yoly/paid_collection_3.png")
                .into(IVPhotoMain);
    }
}
