package yoly.com.android.yoly.ui.helper.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class NewsListVerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int MARGIN = 2;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanIndex = lp.getSpanIndex();

        if (position > 0) {
            if (spanIndex == 1) {
                outRect.left = MARGIN;
            } else {
                outRect.right = MARGIN;
            }

            outRect.bottom = MARGIN * 2;
        }

        /*if (parent.getChildAdapterPosition(view) == 0) {
            outRect.right = VERTICAL_MARGIN * 2;
        } else if (parent.getChildAdapterPosition(view) == 1) {

        } else if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right = VERTICAL_MARGIN * 2;
        } else {
        }*/
    }
}
