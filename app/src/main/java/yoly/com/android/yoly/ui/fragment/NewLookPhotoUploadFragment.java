package yoly.com.android.yoly.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.helper.ClipArt;
import yoly.com.android.yoly.ui.helper.FileHelper;
import yoly.com.android.yoly.ui.view.NewLookView;

import static android.app.Activity.RESULT_OK;

public class NewLookPhotoUploadFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private static final int REQUEST_SELECT_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    private NewLookView view;

    @BindView(R.id.laybg)
    RelativeLayout layBg;

    @BindView(R.id.iv_take_photo)
    ImageView IVTakePhoto;

    @BindView(R.id.tv_greets)
    TextView TVGreets;

    @BindView(R.id.ib_done)
    ImageButton IBDone;

    private Drawable photo;
    private int clipartCount = 1000;
    private String currentPhotoPath;
    private Boolean wasPhotoUploaded = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_look_photo_upload, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                if (null != selectedImageUri) {
                    String path = FileHelper.getPathFromURI(getContext(), selectedImageUri);

                    Glide.with(this)
                            .load(selectedImageUri)
                            .into(new SimpleTarget<GlideDrawable>() {
                                @Override
                                public void onResourceReady(GlideDrawable resource,
                                                            GlideAnimation<? super GlideDrawable> glideAnimation) {
                                    layBg.setBackground(resource.getCurrent());
                                    photo = resource.getCurrent();
                                    wasPhotoUploaded = true;
                                    refreshUI();
                                }
                            });
                }
            }

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                setPic();
            }
        }
    }

    @OnClick(R.id.laybg)
    public void onClipartContainerClick() {
        disableAll();
    }

    @OnClick(R.id.iv_take_photo)
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = FileHelper.createImageFile(getContext());
                currentPhotoPath = photoFile.getAbsolutePath();
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

    @OnClick(R.id.ib_done)
    public void done() {
        view.onPhotoUploaded(photo);
    }

    private void addClipArt() {
        ClipArt clipArt = new ClipArt(getContext(), R.drawable.jeans);

        layBg.addView(clipArt);
        clipArt.setId(clipartCount++);

        clipArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableAll();
            }
        });
    }

    public void disableAll() {
        for (int i = 0; i < layBg.getChildCount(); i++) {
            if (layBg.getChildAt(i) instanceof ClipArt) {
                ((ClipArt) layBg.getChildAt(i)).disableAll();
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = layBg.getWidth();
        int targetH = layBg.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        Drawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
        layBg.setBackground(drawable);
        photo = drawable;
        wasPhotoUploaded = true;
        refreshUI();
    }

    public NewLookPhotoUploadFragment setView(NewLookView view) {
        this.view = view;
        return this;
    }

    private void refreshUI() {
        if (wasPhotoUploaded) {
            IBDone.setVisibility(View.VISIBLE);
            TVGreets.setVisibility(View.GONE);
        }
    }
}
