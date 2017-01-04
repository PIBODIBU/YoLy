package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.helper.ClipArt;

public class MyLookPhotoUploadActivity extends BaseNavigationDrawerActivity {
    private final String TAG = getClass().getSimpleName();
    private static final int REQUEST_SELECT_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @BindView(R.id.laybg)
    RelativeLayout layBg;

    @BindView(R.id.iv_take_photo)
    ImageView IVTakePhoto;

    private int clipartCount = 1000;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_look_photo_upload);

        ButterKnife.bind(this);
        getDrawer();

        addClipArt();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);

                    Glide.with(this)
                            .load(selectedImageUri)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource,
                                                            GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    layBg.setBackground(resource.getCurrent());
                                }
                            });
                }
            }

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                setPic();
            }
        }
    }

    private void addClipArt() {
        ClipArt clipArt = new ClipArt(MyLookPhotoUploadActivity.this, R.drawable.jeans);

        layBg.addView(clipArt);
        clipArt.setId(clipartCount++);

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

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = layBg.getWidth();
        int targetH = layBg.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        layBg.setBackground(new BitmapDrawable(MyLookPhotoUploadActivity.this.getResources(), bitmap));
    }

    @OnClick(R.id.iv_take_photo)
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG, "dispatchTakePictureIntent()-> ", ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
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
