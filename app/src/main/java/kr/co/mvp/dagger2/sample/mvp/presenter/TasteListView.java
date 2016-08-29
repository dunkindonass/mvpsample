package kr.co.mvp.dagger2.sample.mvp.presenter;

import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;

/**
 * Created by 8454 on 2016-08-10.
 */

public interface TasteListView extends BaseMvpView {
    void showData(LocationInfo data);
}
