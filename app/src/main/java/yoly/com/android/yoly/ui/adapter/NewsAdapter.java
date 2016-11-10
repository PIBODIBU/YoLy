package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.NewsComment;
import yoly.com.android.yoly.ui.viewholder.NewsViewHolder;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<NewsComment> dataSet;

    public NewsAdapter(Context context, ArrayList<NewsComment> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsComment dataModel = dataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ArrayList<NewsComment> getDataSet() {
        return dataSet;
    }
}
