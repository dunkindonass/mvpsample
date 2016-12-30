package kr.co.mvp.dagger2.sample.mvp.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import rx.Observable;

/**
 * Created by 8454 on 2016-08-16.
 */

public abstract class BaseMvpFragment extends Fragment implements BaseMvpView {

    @Inject
    protected Activity parentActivity;

    @Inject
    protected PreferenceUtil PREFERENCE;
    @Inject
    protected ProgressDialogProvider progressDialogProvider;

    protected String RootFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        onFragmentInject();
        super.onCreate(savedInstanceState);
    }

    public void onFragmentInject() {
        ((BaseMvpActivity)getActivity()).activityComponent.addFragmentComponent(new FragmentMoudule(this)).inject(this);
    }



    @Override
    public void onResume() {
        ((BaseMvpActivity) parentActivity).setCurrentFragment(this);
        super.onResume();
    }


    @Override
    public <T> Observable.Transformer<T, T> bind() {
        return null;
    }

    @Override
    public <T> Observable.Transformer<T, T> injectProgress() {
        ProgressDialog progressDialog = progressDialogProvider.provide();
        return observable -> observable.doOnSubscribe(progressDialog::show).doOnUnsubscribe(progressDialog::dismiss);
    }

    public void setRootFragment(String tag) {
        this.RootFragment = tag;
    }

    protected abstract String getFragmentTag();

    public String getRootFragmentTag() {
        return RootFragment;
    }

}
