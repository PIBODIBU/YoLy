package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.helper.ClipArt;

public class MyLookActivity extends BaseNavigationDrawerActivity {
    private final String TAG = getClass().getSimpleName();
    private static final int REQUEST_SELECT_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @BindView(R.id.laybg)
    RelativeLayout layBg;

    @BindView(R.id.iv_take_photo)
    ImageView IVTakePhoto;

    int count = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_look);

        ButterKnife.bind(this);
        getDrawer();
        setStatusBarColor(android.R.color.white);

        addClipArt();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);
                    Picasso
                            .with(this)
                            .load(selectedImageUri)
                            .error(R.mipmap.ic_launcher)
                            .into(new Target() {
                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    layBg.setBackground(new BitmapDrawable(MyLookActivity.this.getResources(), bitmap));
                                }

                                @Override
                                public void onBitmapFailed(Drawable errorDrawable) {
                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {

                                }
                            });
                }
            }

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                layBg.setBackground(new BitmapDrawable(MyLookActivity.this.getResources(), photo));
            }
        }
    }

    private void addClipArt() {
        ClipArt clipArt = new ClipArt(MyLookActivity.this, R.drawable.jeans);

        layBg.addView(clipArt);
        clipArt.setId(count++);

        clipArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAll();
            }
        });
    }

    @OnClick(R.id.laybg)
    public void onClipartContainerClick() {
        disableAll();
    }

    @OnClick(R.id.iv_take_photo)
    public void takePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_SELECT_PICTURE);
    }

    @OnClick(R.id.iv_photo_from_gallery)
    public void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_SELECT_PICTURE);
    }

    public void disableAll() {
        for (int i = 0; i < layBg.getChildCount(); i++) {
            if (layBg.getChildAt(i) instanceof ClipArt) {
                ((ClipArt) layBg.getChildAt(i)).disableAll();
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
}
