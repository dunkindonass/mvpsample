package kr.co.mvp.dagger2.sample.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;

/**
 * Created by 8454 on 2016-08-09.
 */


@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, CommonUtilModule.class, NetworkModule.class})
    public interface ApplicationComponent extends AndroidInjector<SampleApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<SampleApplication> {
    }

}
