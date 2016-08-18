package kr.co.mvp.dagger2.sample.mvp.presentor;

import android.content.res.Resources;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 8454 on 2016-08-10.
 */

public class GithubListPresenter extends BasePresentor<GitHubListView> {

    @Inject
    Resources resources;

    @Inject
    NetworkApi networkApi;

    @Inject
    GithubListPresenter() {
    }

    public void requestGithubData(int idx) {

        Observable<LocationInfo> locationSearch = networkApi.getLocation("맛집", "", "OUyULY", "LTr_CV", "1", idx + "", "15", "wcong", "ko", "android", "-1", "true", "1", "3.9.13");
        locationSearch.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(view().injectProgress()).subscribe(new Subscriber<LocationInfo>() {
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
