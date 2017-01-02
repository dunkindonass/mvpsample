package kr.co.mvp.dagger2.sample.mvp.presenter;

import android.content.res.Resources;

import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.mvp.base.BasePresenter;
import kr.co.mvp.dagger2.sample.mvp.database.UserInfoRepository;
import kr.co.mvp.dagger2.sample.mvp.database.vo.UserInfo;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by my on 2016-12-22.
 */

public class TasteListPresenterImpl extends BasePresenter<TasteListView> {

    @Inject
    Resources resources;


    @Inject
    NetworkApi networkApi;


    @Inject
    UserInfoRepository userInfoRepository;


    private ArrayList<Place> searchItems = new ArrayList<>();
    private int index = 1;
    private boolean Loading;
    private int visibleHold = 2;

    @Inject
    TasteListPresenterImpl() {
    }

    public void addUserInfo(List<UserInfo> userInfoList) {
        try {
            userInfoRepository.add(userInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getUserInfo() {
        try {
            view().getUserInfo(userInfoRepository.selectAll());
        } catch (SQLException sqlee) {
            sqlee.printStackTrace();
        }

    }

    public void selectUserInfo(String userclass) {
        try {
            Where<UserInfo, Integer> where = userInfoRepository.getWhere();
            where.eq("userclass", userclass);

            List<UserInfo> userInfos = userInfoRepository.selectWhere(where);
            view().getUserInfo(userInfos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void requestGithubData(int idx) {

        Observable<LocationInfo> locationSearch = null;
        locationSearch = networkApi.getLocation("맛집", "", "OUyULY", "LTr_CV", "1", idx + "", "15", "wcong", "ko", "android", "-1", "true", "1", "3.9.13");

        locationSearch.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(view().injectProgress())
                .compose(view().bind())
                .subscribe(new Subscriber<LocationInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(LocationInfo s) {
                view().showData(s);
            }
        });

    }
}
