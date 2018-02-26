package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.mvp.database.DataHelper;

/**
 * Created by 8454 on 2016-08-10.
 */

@Module
public class CommonUtilModule {

    @Provides
    @Singleton
    public PreferenceUtil providePerferenceUtil(SampleApplication context) {
        return new PreferenceUtil(context);
    }

    @Provides
    @Singleton
    DataHelper provideDatahelper(SampleApplication context) {
        return new DataHelper(context);
    }

}
