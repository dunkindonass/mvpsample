package kr.co.mvp.dagger2.sample.mvp.presenter;

import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;
import kr.co.mvp.dagger2.sample.mvp.views.adapter.GithubReopRecyclerAdapter;

/**
 * Created by imcheol-u on 2018. 3. 3..
 */

public interface GithubReopView extends BaseMvpView {

    void showGithubRepoList(GithubReopRecyclerAdapter githubReopRecyclerAdapter);
}
