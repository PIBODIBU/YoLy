package yoly.com.android.yoly.ui.view;

import android.graphics.drawable.Drawable;

import yoly.com.android.yoly.ui.activity.NewLookActivity;

public interface NewLookView {
    void onPhotoUploaded(Drawable photo);

    void onDismissPressed();

    NewLookActivity getActivity();
}
