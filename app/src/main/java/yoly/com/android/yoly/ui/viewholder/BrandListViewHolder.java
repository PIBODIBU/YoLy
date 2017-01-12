package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class BrandListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_brand_name)
    public TextView TVBrandName;

    @BindView(R.id.ib_deselect)
    public ImageButton IBDeselect;

    public BrandListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
