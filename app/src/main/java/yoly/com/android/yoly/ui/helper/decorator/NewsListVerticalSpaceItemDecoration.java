package yoly.com.android.yoly.ui.helper.decorator;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class NewsListVerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int VERTICAL_MARGIN = 2;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.right = VERTICAL_MARGIN;
        } else if (parent.getChildAdapterPosition(view) == 1) {

        } else if (parent.getChildAdapterPosition(view) % 2 == 0) {

        } else {
            outRect.right = VERTICAL_MARGIN;
        }
    }
}
