package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.mvp.views.MainActivity;
import kr.co.mvp.dagger2.sample.mvp.views.TasteListFragment;
import kr.co.mvp.dagger2.sample.mvp.views.TasteWebFragment;

/**
 * Created by 8454 on 2016-08-09.
 */

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModoule {

    @Binds
    @PerActivity
    abstract Activity activityContext(MainActivity activity);

    @PerFragment
    @ContributesAndroidInjector(modules = FragmentMoudule.class )
    abstract TasteListFragment tasteListFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = FragmentMoudule.class )
    abstract TasteWebFragment tasteWebFragment();



}
