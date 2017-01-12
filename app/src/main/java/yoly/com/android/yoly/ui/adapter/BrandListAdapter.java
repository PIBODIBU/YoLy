package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.Brand;
import yoly.com.android.yoly.ui.viewholder.BrandListViewHolder;

public class BrandListAdapter extends RecyclerView.Adapter<BrandListViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private OnBrandSelectListener onBrandSelectListener;
    private LinkedList<Brand> dataSet;
    private Context context;

    public BrandListAdapter(LinkedList<Brand> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public BrandListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false));
    }

    @Override
    public void onBindViewHolder(final BrandListViewHolder holder, int position) {
        final Brand brand = dataSet.get(position);

        if (brand == null) {
            Log.e(TAG, "onBindViewHolder()-> data set item is null");
            return;
        }

        holder.TVBrandName.setText(brand.getTitle());
        holder.TVBrandName.setTextColor(ContextCompat.getColor(context, brand.isSelected() ? R.color.colorBrandSelected : R.color.colorPrimary));
        holder.TVBrandName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!brand.isSelected()) {
                    onBrandSelectListener.onBrandSelect(brand, holder.getAdapterPosition());
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });
        holder.IBDeselect.setVisibility(brand.isSelected() ? View.VISIBLE : View.GONE);
        holder.IBDeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (brand.isSelected()) {
                    onBrandSelectListener.onBrandDeselect(brand, holder.getAdapterPosition());
                    notifyItemChanged(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public LinkedList<Brand> getDataSet() {
        return dataSet;
    }

    public interface OnBrandSelectListener {
        void onBrandSelect(Brand brand, int position);

        void onBrandDeselect(Brand brand, int position);
    }

    public void setOnBrandSelectListener(OnBrandSelectListener onBrandSelectListener) {
        this.onBrandSelectListener = onBrandSelectListener;
    }
}
