package kr.co.mvp.dagger2.sample.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.mvp.database.DataHelper;

/**
 * Created by my on 2016-12-22.
 */

@Module
public class DataModule {



    @Provides
    @PerActivity
    DataHelper provideDatahelper(Activity activity) {
        return new DataHelper(activity);
    }
}
