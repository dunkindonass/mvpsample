package kr.co.mvp.dagger2.sample.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;

/**
 * Created by 8454 on 2016-08-09.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, CommonUtilModule.class})
public interface ApplicationComponent {

    ActivityComponent addActivityComponent(ActivityModoule activityModoule);

    void inject(SampleApplication application);
}
