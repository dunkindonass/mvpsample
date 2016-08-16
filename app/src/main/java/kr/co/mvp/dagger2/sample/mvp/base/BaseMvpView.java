package kr.co.mvp.dagger2.sample.mvp.base;

import rx.Observable;

/**
 * Created by 8454 on 2016-08-09.
 */

public interface BaseMvpView {


    <T> Observable.Transformer<T, T> bind();

    <T> Observable.Transformer<T, T> injectProgress();

}
