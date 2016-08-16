package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;
import android.app.ProgressDialog;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;

/**
 * Created by 8454 on 2016-08-09.
 */

@Module
public class ActivityModoule {

    private Activity activity;

    public ActivityModoule(Activity activity){
        this.activity=activity;
    }

    @Provides
    @PerActivity
    public Activity activity(){
        return activity;
    }

    @Provides
    @PerActivity
    public ProgressDialogProvider provideProgress(Activity activity){
        return new ProgressDialogProvider(activity);
    }
}
