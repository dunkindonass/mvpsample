package kr.co.mvp.dagger2.sample;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDex;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import kr.co.mvp.dagger2.sample.dagger.component.DaggerApplicationComponent;


/**
 * Created by 8454 on 2016-08-09.
 */

public class SampleApplication extends Application implements HasActivityInjector{


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
   // static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        DaggerApplicationComponent.builder().create(this).inject(this);


        //applicationComponent = createApplicationComponent();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

}
