package kr.co.mvp.dagger2.sample;

import android.app.Application;

import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.component.DaggerApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;

/**
 * Created by 8454 on 2016-08-09.
 */

public class SampleApplication extends Application {


    static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createApplicationComponent();
    }

    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).networkModule(new NetworkModule()).commonUtilModule(new CommonUtilModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
