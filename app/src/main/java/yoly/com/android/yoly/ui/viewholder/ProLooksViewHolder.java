package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class ProLooksViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.background_photo)
    public ImageView IVBgPhoto;

    @BindView(R.id.btn_more)
    public Button BTNMore;

    @BindView(R.id.tv_cost)
    public AppCompatTextView TVCost;

    @BindView(R.id.tv_type)
    public AppCompatTextView TVType;

    @BindView(R.id.tv_season)
    public AppCompatTextView TVSeason;

    public ProLooksViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
