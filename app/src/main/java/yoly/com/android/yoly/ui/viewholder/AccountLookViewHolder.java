package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class AccountLookViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_photo)
    public ImageView IVPhoto;

    public AccountLookViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
