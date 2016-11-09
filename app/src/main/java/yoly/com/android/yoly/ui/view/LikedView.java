package yoly.com.android.yoly.ui.view;

import java.util.ArrayList;

import yoly.com.android.yoly.data.model.LikedModel;
import yoly.com.android.yoly.ui.adapter.LikedAdapter;

public interface LikedView {
    void setupRecyclerView();

    ArrayList<LikedModel> getDataSet();

    LikedAdapter getAdapter();
}
