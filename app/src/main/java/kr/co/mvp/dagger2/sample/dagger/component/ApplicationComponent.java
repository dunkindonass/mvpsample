package kr.co.mvp.dagger2.sample.dagger.component;

import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Component;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.CommonUtilModule;
import kr.co.mvp.dagger2.sample.dagger.module.NetworkModule;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;

/**
 * Created by 8454 on 2016-08-09.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, CommonUtilModule.class})
public interface ApplicationComponent {



    ActivityComponent addActivtiyComponent(ActivityModoule activityModoule);

    NetworkApi networkapi();

    PreferenceUtil preferenceutil();

    Resources resouce();

    void inject(SampleApplication application);
}
