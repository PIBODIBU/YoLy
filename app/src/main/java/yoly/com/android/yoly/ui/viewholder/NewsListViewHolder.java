package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class NewsListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title)
    public TextView TVTitle;

    @BindView(R.id.tv_comments_num)
    public TextView TVCommentsNum;

    @BindView(R.id.tv_date)
    public TextView TVDate;

    @BindView(R.id.image_view)
    public ImageView IVPhoto;

    public NewsListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
