package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.AccountLookModel;
import yoly.com.android.yoly.ui.viewholder.AccountLookViewHolder;

public class AccountLookAdapter extends RecyclerView.Adapter<AccountLookViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<AccountLookModel> dataSet;
    private Context context;

    public AccountLookAdapter(ArrayList<AccountLookModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public AccountLookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountLookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_look, parent, false));
    }

    @Override
    public void onBindViewHolder(AccountLookViewHolder holder, int position) {
        AccountLookModel model = dataSet.get(position);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        Picasso
                .with(context)
                .load(model.getPhotoUrl())
                .into(holder.IVPhoto);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }
}
