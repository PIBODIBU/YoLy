package yoly.com.android.yoly.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.helper.IntentKeys;
import yoly.com.android.yoly.ui.dialog.bottomsheet.TakeOrLoadPhotoDialog;
import yoly.com.android.yoly.ui.view.AfterRegisterView;

public class AfterRegisterActivity extends AppCompatActivity implements AfterRegisterView {

    private static final int REQUEST_SELECT_PICTURE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @BindView(R.id.tv_greets)
    public TextView TVGreets;

    @BindView(R.id.tv_email)
    public TextView TVEmail;

    @BindString(R.string.tv_successfully_registered)
    public String greetMessage;

    @BindString(R.string.tv_successfully_registered_filter)
    public String greetMessageFilter;

    @BindView(R.id.civ_profile_photo)
    public CircleImageView CIVProfilePhoto;

    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_register);
        ButterKnife.bind(this);

        if (getIntent() == null || getIntent().getExtras() == null) {
            finish();
            return;
        }

        if (!getIntent().getExtras().containsKey(IntentKeys.NAME) || getIntent().getExtras().containsKey(IntentKeys.EMAIL)) {
            finish();
            return;
        }

        name = getIntent().getExtras().getString(IntentKeys.NAME);
        email = getIntent().getExtras().getString(IntentKeys.EMAIL);

        TVGreets.setText(greetMessage.replace(greetMessageFilter, name));
        TVEmail.setText(email);
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
                            .into(CIVProfilePhoto);
                }
            }

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                CIVProfilePhoto.setImageBitmap(photo);
            }
        }
    }

    @OnClick(R.id.btn_continue)
    public void startUsing() {
        startActivity(new Intent(AfterRegisterActivity.this, NewsListActivity.class));
        finish();
    }

    @Override
    @OnClick(R.id.civ_profile_photo)
    public void pickImageFromGallery() {
        TakeOrLoadPhotoDialog dialog = new TakeOrLoadPhotoDialog();
        dialog.setOnItemClickedListener(new TakeOrLoadPhotoDialog.OnItemClickedListener() {
            @Override
            public void pickImageFromGallery() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_SELECT_PICTURE);
            }

            @Override
            public void takePhotoFromCamera() {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
        dialog.show(getSupportFragmentManager(), "TakeOrLoadPhotoDialog");
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
