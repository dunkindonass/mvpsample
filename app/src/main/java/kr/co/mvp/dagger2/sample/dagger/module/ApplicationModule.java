package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.mvp.views.MainActivity;

/**
 * Created by 8454 on 2016-08-09.
 */

@Module(includes = AndroidInjectionModule.class)
public abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract Application provideContext(SampleApplication application) ;

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModoule.class)
    abstract MainActivity mainActivity();



}
