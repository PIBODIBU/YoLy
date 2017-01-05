package yoly.com.android.yoly.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.knef.stickerview.StickerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.view.NewLookView;

public class NewLookWorkspaceFragment extends Fragment {
    private Drawable photo;
    private NewLookView view;

    @BindView(R.id.laybg)
    RelativeLayout canvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_look_workspace, container, false);
        ButterKnife.bind(this, rootView);

        canvas.setBackground(photo);

        StickerImageView sticker = new StickerImageView(getContext());
        sticker.setImageResource(R.drawable.jeans);
        canvas.addView(sticker);

        return rootView;
    }

    @OnClick(R.id.iv_dismiss)
    public void dismiss() {
        view.onDismissPressed();
    }

    public NewLookWorkspaceFragment setPhoto(Drawable photo) {
        this.photo = photo;
        return this;
    }

    public NewLookWorkspaceFragment setView(NewLookView view) {
        this.view = view;
        return this;
    }
}
