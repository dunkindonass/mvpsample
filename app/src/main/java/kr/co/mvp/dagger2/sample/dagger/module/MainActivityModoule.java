package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListPresenterImpl;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListView;
import kr.co.mvp.dagger2.sample.mvp.views.MainActivity;
import kr.co.mvp.dagger2.sample.mvp.views.TasteListFragment;
import kr.co.mvp.dagger2.sample.mvp.views.TasteWebFragment;

/**
 * Created by 8454 on 2016-08-09.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModoule {

    @PerFragment
    @ContributesAndroidInjector(modules = FragmentMoudule.class )
    abstract TasteListFragment tasteListFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = FragmentMoudule.class )
    abstract TasteWebFragment tasteWebFragment();

    @Binds
    @PerActivity
    abstract Activity activityContext(MainActivity activity);

    @Provides
    @PerActivity
    static ProgressDialogProvider provideProgress(Activity activity){
        return new ProgressDialogProvider(activity);
    }

    @Provides
    @PerActivity
    static Resources resources(Activity activity){
        return activity.getApplicationContext().getResources();

    }

}
