package kr.co.mvp.dagger2.sample.dagger.module;



import android.app.Fragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.mvp.views.TasteListFragment;

/**
 * Created by 8454 on 2016-08-16.
 */

@Module
public abstract class FragmentMoudule {

    @Binds
    @PerFragment
    abstract Fragment fragment(TasteListFragment fragment);


}
