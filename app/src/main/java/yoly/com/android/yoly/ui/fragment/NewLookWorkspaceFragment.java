package yoly.com.android.yoly.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.knef.stickerview.StickerImageView;
import com.knef.stickerview.StickerView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.activity.BrandChooseActivity;
import yoly.com.android.yoly.ui.fragment.pager.NewLookEditModeSettings1;
import yoly.com.android.yoly.ui.fragment.pager.NewLookEditModeSettings2;
import yoly.com.android.yoly.ui.view.NewLookView;

public class NewLookWorkspaceFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private Boolean inEditMode = false;
    private StickerImageView activeItem = null;
    private Drawable photo;
    private NewLookView view;

    // Views
    @BindView(R.id.laybg)
    RelativeLayout canvas;

    @BindView(R.id.hamburger)
    ImageButton hamburger;

    // View mode controls
    @BindView(R.id.iv_dismiss)
    ImageView IVDismiss;

    // Edit mode controls
    @BindView(R.id.rl_header_edit_mode)
    RelativeLayout RLEditModeHeader;

    @BindView(R.id.ib_back)
    ImageButton IBBack;

    @BindView(R.id.rl_edit_mode_controls)
    RelativeLayout RLEditModePanel;

    @BindView(R.id.ib_edit_mode_done)
    ImageButton IBEditModeDone;

    @BindView(R.id.ib_edit_mode_delete)
    ImageButton IBEditModeDelete;

    @BindView(R.id.ib_edit_mode_settings)
    ImageButton IBEditModeSettings;

    @BindView(R.id.view_pager)
    ViewPager viewPagerEditMode;

    @BindView(R.id.page_indicator_container)
    RelativeLayout RLPageIndicatorWrapper;

    @BindView(R.id.page_indicator)
    CircleIndicator pagerIndicatorEditMode;

    // View mode controls
    @BindView(R.id.rl_controls)
    RelativeLayout RLPanelChooseBrand;

    // Bottom sheets
    @BindView(R.id.bottom_sheet_edit_mode_settings)
    FrameLayout bottomSheetEditModeSettings;

    private BottomSheetBehavior bottomSheetBehaviorEditModeSettings;
    private NewLookEditModeSettings1 settingsEditMode1;
    private NewLookEditModeSettings2 settingsEditMode2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_look_workspace, container, false);
        ButterKnife.bind(this, rootView);

        view.getActivity().setHamburger(hamburger);

        settingsEditMode1 = new NewLookEditModeSettings1();
        settingsEditMode2 = new NewLookEditModeSettings2();

        EditModeSettingAdapter pageAdapter = new EditModeSettingAdapter(getFragmentManager());
        pageAdapter.addPage(settingsEditMode1);
        pageAdapter.addPage(settingsEditMode2);
        viewPagerEditMode.setAdapter(pageAdapter);
        pagerIndicatorEditMode.setViewPager(viewPagerEditMode);

        bottomSheetBehaviorEditModeSettings = BottomSheetBehavior.from(bottomSheetEditModeSettings);
        bottomSheetBehaviorEditModeSettings.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    RLPageIndicatorWrapper.setVisibility(View.GONE);
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    RLPageIndicatorWrapper.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        bottomSheetBehaviorEditModeSettings.setState(BottomSheetBehavior.STATE_HIDDEN);

        IBEditModeDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActiveItem() != null)
                    getActiveItem().setInEditMode(false);
                setInEditMode(false);
            }
        });

        IBEditModeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActiveItem() != null)
                    getActiveItem().delete();
                setInEditMode(false);
            }
        });

        IBEditModeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehaviorEditModeSettings.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        addDemoItem();
        addDemoItem();
        addDemoItem();
        setInEditMode(false);

        return rootView;
    }

    private void addDemoItem() {
        canvas.setBackground(photo);
        final StickerImageView sticker = new StickerImageView(getContext());
        sticker.setImageResource(R.drawable.jeans);
        sticker.setOnDeleteListener(new StickerView.OnDeleteListener() {
            @Override
            public void onDelete() {
                setInEditMode(false);
            }
        });
        sticker.setNormalModeTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !getInEditMode()) {
                    MenuBuilder menuBuilder = new MenuBuilder(getContext());
                    MenuInflater menuInflater = new MenuInflater(getContext());
                    final MenuPopupHelper menuPopupHelper = new MenuPopupHelper(getContext(), menuBuilder);

                    menuInflater.inflate(R.menu.new_look_workspace_context, menuBuilder);
                    menuPopupHelper.setAnchorView(sticker);
                    menuPopupHelper.setForceShowIcon(true);
                    menuPopupHelper.show();

                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_edit:
                                    ((ImageView) sticker.getMainView()).setAlpha(1F);
                                    setActiveItem(sticker);
                                    setInEditMode(true);
                                    sticker.setInEditMode(true);
                                    return true;

                                case R.id.action_delete:
                                    sticker.delete();
                                    return true;
                            }

                            return false;
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {
                        }
                    });
                    menuPopupHelper.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            ((ImageView) sticker.getMainView()).setAlpha(1F);
                        }
                    });

                    ((ImageView) sticker.getMainView()).setAlpha(0.5F);

                    return true;
                }

                return false;
            }
        });
        sticker.setInEditMode(false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(500, 500);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        canvas.addView(sticker, layoutParams);
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

        settingsEditMode1.setActiveItem(activeItem);
        settingsEditMode2.setActiveItem(activeItem);
    }

    public Boolean getInEditMode() {
        return inEditMode;
    }

    public void setInEditMode(Boolean inEditMode) {
        this.inEditMode = inEditMode;

        if (inEditMode) {
            setEditModeControlsVisible(true);
        } else {
            setEditModeControlsVisible(false);
        }
    }

    private void setEditModeControlsVisible(Boolean visible) {
        if (visible) {
            RLEditModePanel.setVisibility(View.VISIBLE);
            RLEditModeHeader.setVisibility(View.VISIBLE);
            IVDismiss.setVisibility(View.GONE);
        } else {
            RLEditModePanel.setVisibility(View.GONE);
            RLEditModeHeader.setVisibility(View.GONE);
            bottomSheetBehaviorEditModeSettings.setState(BottomSheetBehavior.STATE_HIDDEN);
            IVDismiss.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.rl_controls)
    public void openCategoryChoosePanel() {
        startActivity(new Intent(getContext(), BrandChooseActivity.class));
    }

    private class EditModeSettingAdapter extends FragmentPagerAdapter {
        private LinkedList<Fragment> fragments;

        public EditModeSettingAdapter(FragmentManager fm) {
            super(fm);
            fragments = new LinkedList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addPage(Fragment fragment) {
            fragments.add(fragment);
        }
    }
}