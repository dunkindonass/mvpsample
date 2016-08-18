package kr.co.mvp.dagger2.sample.dagger.component;

import dagger.Subcomponent;
import kr.co.mvp.dagger2.sample.dagger.PerFragment;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.views.TasteListFragment;

/**
 * Created by 8454 on 2016-08-09.
 */

@PerFragment
@Subcomponent(modules = FragmentMoudule.class)
public interface FragmentComponent {

    void inject(BaseMvpFragment baseMvpFragment);
    void inject(TasteListFragment tasteListFragment);

}
