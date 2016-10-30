package yoly.com.android.yoly.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;

public class CountryListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_country)
    public TextView TVCountry;

    public CountryListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
