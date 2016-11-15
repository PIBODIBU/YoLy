package yoly.com.android.yoly.ui.dialog.bottomsheet;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;

public class TakeOrLoadPhotoDialog extends BottomSheetDialogFragment {
    private OnItemClickedListener onItemClickedListener;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet_photo, null);

        ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);

/*        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }*/
    }

    @OnClick(R.id.tv_load_photo)
    public void pickImageFromGallery() {
        if (onItemClickedListener != null) {
            onItemClickedListener.pickImageFromGallery();
            dismiss();
        }
    }

    @OnClick(R.id.tv_take_photo)
    public void takePhotoFromCamera() {
        if (onItemClickedListener != null) {
            onItemClickedListener.takePhotoFromCamera();
            dismiss();
        }
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void pickImageFromGallery();

        void takePhotoFromCamera();
    }
}
