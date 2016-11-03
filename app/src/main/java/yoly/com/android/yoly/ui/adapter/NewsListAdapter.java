package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.ui.viewholder.NewsListViewHolder;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<NewsModel> dataSet;

    public NewsListAdapter(Context context, ArrayList<NewsModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsListViewHolder holder, int position) {
        NewsModel newsModel = dataSet.get(position);

        holder.TVTitle.setText(newsModel.getTitle());
        holder.TVDate.setText(newsModel.getDate());
        holder.TVCommentsNum.setText(String.valueOf(newsModel.getCommentsNum()));

        Picasso
                .with(context)
                .load(newsModel.getPhotoUrl())
                .into(holder.IVPhoto);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ArrayList<NewsModel> getDataSet() {
        return dataSet;
    }
}
