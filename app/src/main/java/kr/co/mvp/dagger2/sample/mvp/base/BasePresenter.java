package kr.co.mvp.dagger2.sample.mvp.base;


import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;

/**
 * Created by 8454 on 2016-08-10.
 */

public class BasePresenter<VIEW extends BaseMvpView>{

    private VIEW view;

    public void attach( VIEW view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public VIEW view() {
        return this.view;
    }
}
