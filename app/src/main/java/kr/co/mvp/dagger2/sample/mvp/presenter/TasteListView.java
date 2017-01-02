package kr.co.mvp.dagger2.sample.mvp.presenter;

import java.util.List;

import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;
import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.views.TasteRecyclerAdapter;

/**
 * Created by 8454 on 2016-08-10.
 */

public interface TasteListView extends BaseMvpView {

    void showData(LocationInfo data);

    void getUserInfo(List<UserInfo> userInfoList);

    void setRecyclerView(TasteRecyclerAdapter tasteRecyclerAdapter);

}
