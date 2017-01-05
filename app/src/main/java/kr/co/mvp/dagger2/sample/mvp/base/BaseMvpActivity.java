package kr.co.mvp.dagger2.sample.mvp.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import javax.inject.Inject;

import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ActivityComponent;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import kr.co.mvp.dagger2.sample.nondagger.BaseFragment;
import kr.co.mvp.dagger2.sample.utils.StringUtils;
import rx.Observable;

/**
 * Created by 8454 on 2016-08-09.
 */

public class BaseMvpActivity extends FragmentActivity implements BaseMvpView {

    protected BaseMvpFragment currentFragment;

    protected String CURRENTTAG;

    @Inject
    protected PreferenceUtil PREFERENCE;

   /* @Inject
    protected ProgressDialogProvider progressDialogProvider;*/

    public static final String ROOTFRAGMENT = "rootfragment";

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         onInject();
        super.onCreate(savedInstanceState);

    }

    public void onInject() {
        activityComponent= SampleApplication.component(this).addActivtiyComponent(new ActivityModoule(this));
        activityComponent.inject(this);
    }

    @Override
    public <T> Observable.Transformer<T, T> bind() {
        return null;
    }

    @Override
    public <T> Observable.Transformer<T, T> injectProgress() {
        ProgressDialog progressDialog = new ProgressDialogProvider(this).provide();
        return observable -> observable.doOnSubscribe(progressDialog::show).doOnUnsubscribe(progressDialog::dismiss);
    }

    public void onCallFragment(BaseMvpFragment fragment, String root, Bundle data) {

        try {
            CURRENTTAG = fragment.getFragmentTag();

            fragment.setRootFragment(root);

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

            if (data != null) {
                fragment.setArguments(data);
            }
            ft.replace(R.id.container_layout, fragment, fragment.getFragmentTag());
            ft.addToBackStack(fragment.getFragmentTag());

            ft.commitAllowingStateLoss();
            currentFragment = fragment;
        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }

    public void onCallFragment(BaseFragment fragment, String root, Bundle data) {

        try {
            CURRENTTAG = fragment.getFragmentTag();

            fragment.setRootFragment(root);

            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

            if (data != null) {
                fragment.setArguments(data);
            }
            ft.replace(R.id.container_layout, fragment, fragment.getFragmentTag());
            ft.addToBackStack(fragment.getFragmentTag());

            ft.commitAllowingStateLoss();
            //currentFragment = fragment;

        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }

    public void popupStack(BaseMvpFragment currentFragment) {

        String ROOTTAG = currentFragment.getRootFragmentTag();
        if (!StringUtils.isNullOrEmpty(ROOTTAG)) {
            //MainFragment 까지 back
            //getSupportFragmentManager().popBackStack(MAINFRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            //Rootfragment없을때
            Log.e("else", "call");
            String currentTag = currentFragment.getFragmentTag();
            getFragmentManager()
                    .popBackStack(currentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }


    @Override
    public void onBackPressed() {
        //메인화면일때 종료
        String rootTag = currentFragment.getRootFragmentTag();

        if (ROOTFRAGMENT.equals(rootTag)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(BaseMvpActivity.this);
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.setMessage("exit app?");
            alert.show();
         /*   KumonCommonPopup kumonCommonPopup = new KumonCommonPopup(this, TWOBUTTON);
            kumonCommonPopup.setPopupText(getResources().getString(R.string.app_exit_question));
            kumonCommonPopup.setListener(null, () -> finish());
            kumonCommonPopup.DialogShow();*/
        } else {
            popupStack(currentFragment);
        }

    }

    public void setCurrentFragment(BaseMvpFragment fragment) {
        this.currentFragment = fragment;
    }

    public ActivityComponent getActivityComponent(){
        return activityComponent;
    }

}
