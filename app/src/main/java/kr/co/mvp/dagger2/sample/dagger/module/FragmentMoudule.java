package kr.co.mvp.dagger2.sample.dagger.module;



import android.app.Fragment;

import dagger.Module;
import dagger.Provides;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;

/**
 * Created by 8454 on 2016-08-16.
 */

@Module
public class FragmentMoudule {
    private final Fragment fragment;


    public FragmentMoudule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return fragment;
    }

}
