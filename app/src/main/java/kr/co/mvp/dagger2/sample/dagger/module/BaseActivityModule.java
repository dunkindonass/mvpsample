package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;

/**
 * Created by a8454 on 2018. 2. 26..
 */


@Module
public abstract class BaseActivityModule {

    @Binds
    @PerActivity
    abstract Context activityContext(Activity activity);

    @Provides
    @PerActivity
    static ProgressDialogProvider provideProgress(Context context){
        return new ProgressDialogProvider(context);
    }

    @Provides
    @PerActivity
    static Resources resources(Context context){
        return context.getApplicationContext().getResources();

    }




}
