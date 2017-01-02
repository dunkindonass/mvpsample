package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListPresenterImpl;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListView;

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
