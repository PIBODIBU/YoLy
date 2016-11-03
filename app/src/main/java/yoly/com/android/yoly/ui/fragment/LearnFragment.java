package yoly.com.android.yoly.ui.fragment;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class LearnFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.slide_description)
    public TextView TVDescription;

    @BindView(R.id.image_view)
    public ImageView IVImage;

    private int imageRes;
    private String description;

    public LearnFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_learn, container, false);
        ButterKnife.bind(this, rootView);

        Picasso
                .with(getContext())
                .load(imageRes)
                .into(IVImage);
        TVDescription.setText(description);

        return rootView;
    }

    public LearnFragment setImageRes(@DrawableRes int imageRes) {
        this.imageRes = imageRes;
        return this;
    }

    public LearnFragment setDescription(String description) {
        this.description = description;
        return this;
    }
}
