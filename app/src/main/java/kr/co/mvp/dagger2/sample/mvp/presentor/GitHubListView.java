package kr.co.mvp.dagger2.sample.mvp.presentor;

import java.util.ArrayList;

import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;

/**
 * Created by 8454 on 2016-08-10.
 */

public interface GitHubListView extends BaseMvpView {
    void showData(LocationInfo data);
}
