package kr.co.mvp.dagger2.sample.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.SampleApplication;

/**
 * Created by 8454 on 2016-08-09.
 */

@Module
public class ApplicationModule {

    private final SampleApplication application;

    public ApplicationModule(SampleApplication application){
        this.application=application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(){return application.getApplicationContext();}


    @Provides
    @Singleton
    Resources provideResouces(){return application.getResources();}



}
