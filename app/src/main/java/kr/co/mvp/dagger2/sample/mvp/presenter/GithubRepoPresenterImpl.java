package kr.co.mvp.dagger2.sample.mvp.presenter;

import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.mvp.base.BasePresenter;
import kr.co.mvp.dagger2.sample.mvp.model.GitHubRepotInfo;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.views.adapter.GithubReopRecyclerAdapter;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by imcheol-u on 2018. 3. 3..
 */

public class GithubRepoPresenterImpl extends BasePresenter<GithubReopView> {

    @Inject
    NetworkApi networkApi;

    @Inject
    Resources resources;

    @Inject
    GithubReopRecyclerAdapter githubReopRecyclerAdapter;

    @Inject
    public GithubRepoPresenterImpl() {
    }

    public void getReopList(String q, String sort, String order) {

        networkApi.getRepositoryList(q, sort, order).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(view().injectProgress())
                .compose(view().bind())
                .subscribe(new Subscriber<GitHubRepotInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GitHubRepotInfo gitHubRepotInfo) {

                        if(gitHubRepotInfo.getItems().size()>0){
                            Log.e("response:::",new Gson().toJson(gitHubRepotInfo.getItems()));
                            githubReopRecyclerAdapter.setData(gitHubRepotInfo.getItems());
                        }
                        view().showGithubRepoList(githubReopRecyclerAdapter);
                    }
                });


    }
}
