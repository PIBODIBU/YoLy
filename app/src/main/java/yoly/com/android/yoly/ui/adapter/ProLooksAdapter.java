package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.ProLookModel;
import yoly.com.android.yoly.ui.viewholder.ProLooksViewHolder;

public class ProLooksAdapter extends RecyclerView.Adapter<ProLooksViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<ProLookModel> dataSet;
    private Context context;

    public ProLooksAdapter(ArrayList<ProLookModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ProLooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProLooksViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pro_look, parent, false));
    }

    @Override
    public void onBindViewHolder(ProLooksViewHolder holder, int position) {
        ProLookModel model = dataSet.get(position);
        String rubChar = context.getResources().getString(R.string.rub);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        holder.TVCost.setText(model.getCost().concat(rubChar));
        holder.TVType.setText(model.getType());
        holder.TVSeason.setText(model.getSeason());

        Picasso
                .with(context)
                .load(model.getPhotoUrl())
                .into(holder.IVBgPhoto);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }
}
