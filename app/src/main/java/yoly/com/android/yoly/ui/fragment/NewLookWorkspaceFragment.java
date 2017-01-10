package yoly.com.android.yoly.ui.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupMenuCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.knef.stickerview.StickerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.view.NewLookView;

public class NewLookWorkspaceFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private Boolean inEditMode = false;
    private StickerImageView activeItem = null;
    private Drawable photo;
    private NewLookView view;

    @BindView(R.id.laybg)
    RelativeLayout canvas;

    @BindView(R.id.hamburger)
    ImageButton hamburger;


    @BindView(R.id.action_edit_done)
    ImageButton IBEditDone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_look_workspace, container, false);
        ButterKnife.bind(this, rootView);

        view.getActivity().setHamburger(hamburger);

        IBEditDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActiveItem().setInEditMode(false);
                setInEditMode(false);
            }
        });

        canvas.setBackground(photo);
        final StickerImageView sticker = new StickerImageView(getContext());
        sticker.setImageResource(R.drawable.jeans);
        sticker.setNormalModeTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    MenuBuilder menuBuilder = new MenuBuilder(getContext());
                    MenuInflater menuInflater = new MenuInflater(getContext());
                    MenuPopupHelper menuPopupHelper = new MenuPopupHelper(getContext(), menuBuilder);

                    menuInflater.inflate(R.menu.new_look_workspace_context, menuBuilder);
                    menuPopupHelper.setAnchorView(sticker);
                    menuPopupHelper.setForceShowIcon(true);
                    menuPopupHelper.show();

                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_edit:
                                    setActiveItem(sticker);
                                    setInEditMode(true);
                                    sticker.setInEditMode(true);
                                    return true;
                            }
                            return false;
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {

                        }
                    });

                 /*   PopupMenu popupMenu = new PopupMenu(getContext(), sticker);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_edit:
                                    setActiveItem(sticker);
                                    setInEditMode(true);
                                    sticker.setInEditMode(true);
                                    return true;
                            }
                            return false;
                        }
                    });
                    popupMenu.inflate(R.menu.new_look_workspace_context);
                    popupMenu.show();*/

                    return true;
                }

                return false;
            }
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500, 500);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        canvas.addView(sticker, layoutParams);
        setInEditMode(true);
        setActiveItem(sticker);

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

    public StickerImageView getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(StickerImageView activeItem) {
        this.activeItem = activeItem;
    }

    public Boolean getInEditMode() {
        return inEditMode;
    }

    public void setInEditMode(Boolean inEditMode) {
        this.inEditMode = inEditMode;

        if (inEditMode) {
            IBEditDone.setVisibility(View.VISIBLE);
        } else {
            IBEditDone.setVisibility(View.GONE);
        }
    }
}