package yoly.com.android.yoly.ui.fragment.pager;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.knef.stickerview.StickerImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class NewLookEditModeSettings1 extends Fragment {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.seek_bar_brightness)
    AppCompatSeekBar seekBarBrightness;

    @BindView(R.id.seek_bar_contrast)
    AppCompatSeekBar seekBarContrast;

    private StickerImageView activeItem;

    private int brightness = 0;
    private int contrast = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_look_settings_1, null);
        ButterKnife.bind(this, view);

        return view;
    }

    public NewLookEditModeSettings1 setActiveItem(final StickerImageView activeItem) {
        this.activeItem = activeItem;

        seekBarBrightness.setMax(510);
        seekBarBrightness.setProgress(510 / 2);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    ((ImageView) activeItem.getMainView()).setColorFilter(brightImageView((progress -= 255)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarContrast.setMax(10);
        seekBarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    ((ImageView) activeItem.getMainView()).setColorFilter(contrastImageView(progress));
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

    public ColorMatrixColorFilter brightImageView(int brightness) {
        this.brightness = brightness;

        ColorMatrix cmB = new ColorMatrix();
        cmB.set(new float[]{
                this.contrast, 0, 0, 0, brightness,
                0, this.contrast, 0, 0, brightness,
                0, 0, this.contrast, 0, brightness,
                0, 0, 0, 1, 0});

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(cmB);
        return new ColorMatrixColorFilter(colorMatrix);
    }

    public ColorMatrixColorFilter contrastImageView(int contrast) {
        this.contrast = contrast;

        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        contrast, 0, 0, 0, this.brightness,
                        0, contrast, 0, 0, this.brightness,
                        0, 0, contrast, 0, this.brightness,
                        0, 0, 0, 1, 0
                });

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(cm);
        return new ColorMatrixColorFilter(colorMatrix);
    }
}
