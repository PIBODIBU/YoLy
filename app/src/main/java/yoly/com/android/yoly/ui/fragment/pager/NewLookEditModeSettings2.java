package yoly.com.android.yoly.ui.fragment.pager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.knef.stickerview.StickerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class NewLookEditModeSettings2 extends Fragment {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.seek_bar_brightness)
    AppCompatSeekBar seekBarBrightness;

    private StickerImageView activeItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_look_settings_2, null);
        ButterKnife.bind(this, view);

        return view;
    }

    public NewLookEditModeSettings2 setActiveItem(final StickerImageView activeItem) {
        this.activeItem = activeItem;

        seekBarBrightness.setMax(100);
        seekBarBrightness.setProgress(((int) activeItem.getAlpha()) * 100);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    ((ImageView) activeItem.getMainView()).setAlpha(progress / 100F);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return this;
    }
}
