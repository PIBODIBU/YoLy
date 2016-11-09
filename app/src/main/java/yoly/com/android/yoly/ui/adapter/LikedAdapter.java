package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.LikedModel;
import yoly.com.android.yoly.ui.viewholder.LikedViewHolder;

public class LikedAdapter extends RecyclerView.Adapter<LikedViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<LikedModel> dataSet;
    private OnItemClickListener onItemClickListener;

    public LikedAdapter(Context context, ArrayList<LikedModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public LikedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LikedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liked, parent, false));
    }

    @Override
    public void onBindViewHolder(final LikedViewHolder holder, int position) {
        LikedModel likedModel = dataSet.get(position);

        holder.IBDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onDelete(holder.getAdapterPosition());
            }
        });

        Picasso
                .with(context)
                .load(likedModel.getPhotoUrl())
                .into(holder.IVPhoto);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ArrayList<LikedModel> getDataSet() {
        return dataSet;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onDelete(int position);
    }
}
