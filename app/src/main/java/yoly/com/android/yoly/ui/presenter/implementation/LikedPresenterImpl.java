package yoly.com.android.yoly.ui.presenter.implementation;

import yoly.com.android.yoly.data.model.LikedModel;
import yoly.com.android.yoly.ui.presenter.LikedPresenter;
import yoly.com.android.yoly.ui.view.LikedView;

public class LikedPresenterImpl implements LikedPresenter {
    private LikedView view;

    public LikedPresenterImpl(LikedView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setupRecyclerView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void fillDataSet() {
        view.getAdapter().getDataSet().add(
                new LikedModel("http://images.nike.com/is/image/DotCom/PDP_HERO_M/819685_010_A_PREM/air-huarache-ultra-mens-shoe.jpg"));
        view.getAdapter().getDataSet().add(
                new LikedModel("http://images3.nike.com/is/image/DotCom/PDP_HERO/599409_007_C_PREM/air-max-thea-womens-shoe.jpg"));
        view.getAdapter().getDataSet().add(
                new LikedModel("http://images.nike.com/is/image/DotCom/PDP_HERO_M/819476_001_A_PREM/D0BAD180D0BED181D181D0BED0B2D0BAD0B8-air-max-1-ultra-essential.jpg"));
        view.getAdapter().getDataSet().add(
                new LikedModel("http://fair.pittopit.ru/wp-content/uploads/2015/09/01.jpg"));
        view.getAdapter().getDataSet().add(
                new LikedModel("https://s-media-cache-ak0.pinimg.com/564x/bb/7f/cf/bb7fcf28e6463321a856134ffc67901a.jpg"));

        view.getAdapter().notifyDataSetChanged();
    }
}
