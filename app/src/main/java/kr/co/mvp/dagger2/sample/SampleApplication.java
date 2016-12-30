package kr.co.mvp.dagger2.sample;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.component.DaggerApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;

/**
 * Created by 8454 on 2016-08-09.
 */

public class SampleApplication extends MultiDexApplication {


    static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);


        applicationComponent = createApplicationComponent();
    }

    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).networkModule(new NetworkModule()).commonUtilModule(new CommonUtilModule(this)).build();

    }

    public static ApplicationComponent component(Context context) {
        return ((SampleApplication) context.getApplicationContext()).applicationComponent;
    }


}
