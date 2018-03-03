package kr.co.mvp.dagger2.sample.dagger.module;



import android.app.Fragment;

import dagger.Binds;
import dagger.Module;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;

/**
 * Created by 8454 on 2016-08-16.
 */

@Module
public abstract class FragmentMoudule {

    @Binds
    @PerFragment
    abstract Fragment fragment(Fragment fragment);


}
