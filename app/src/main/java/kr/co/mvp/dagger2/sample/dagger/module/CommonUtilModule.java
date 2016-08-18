package kr.co.mvp.dagger2.sample.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;

/**
 * Created by 8454 on 2016-08-10.
 */

@Module
public class CommonUtilModule {
    Context context;

    public CommonUtilModule(SampleApplication application) {
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public PreferenceUtil providePerferenceUtil() {
        return new PreferenceUtil(context);
    }
}
