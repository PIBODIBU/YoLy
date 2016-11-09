package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class LikedViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ib_delete)
    public ImageButton IBDelete;

    @BindView(R.id.image_view)
    public ImageView IVPhoto;

    public LikedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
