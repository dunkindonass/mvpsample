package kr.co.mvp.dagger2.sample.nondagger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trello.rxlifecycle.components.RxFragment;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpView;
import kr.co.mvp.dagger2.sample.utils.Subscriptionutil;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 8454 on 2016-08-16.
 */

public abstract class BaseFragment extends RxFragment implements BaseMvpView {

    protected Activity parentActivity;

    protected PreferenceUtil PREFERENCE;

    protected ProgressDialogProvider progressDialogProvider;

    protected String RootFragment;

    private CompositeSubscription subscriptions=new CompositeSubscription();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultSetting();
    }

    public void defaultSetting(){
        PREFERENCE=new PreferenceUtil(parentActivity);
        progressDialogProvider=new ProgressDialogProvider(parentActivity);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        parentActivity=getActivity();
    }

    @Override
    public void onResume() {
       // ((BaseMvpActivity) parentActivity).setCurrentFragment(this);
        super.onResume();
    }


    @Override
    public <T> Observable.Transformer<T, T> bind() {
        return observable -> observable.compose(bindToLifecycle()).lift(Subscriptionutil.composite(subscriptions)) ;
    }

    @Override
    public <T> Observable.Transformer<T, T> injectProgress() {
        ProgressDialog progressDialog = progressDialogProvider.provide();
        return observable -> observable.doOnSubscribe(progressDialog::show).doOnUnsubscribe(progressDialog::dismiss);
    }

    public void setRootFragment(String tag) {
        this.RootFragment = tag;
    }

    public abstract String getFragmentTag();

    public String getRootFragmentTag() {
        return RootFragment;
    }

}
