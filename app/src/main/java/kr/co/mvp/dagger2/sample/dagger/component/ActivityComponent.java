package kr.co.mvp.dagger2.sample.dagger.component;

import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;

/**
 * Created by 8454 on 2016-08-09.
 */

//@PerActivity
//@Subcomponent(modules = {ActivityModoule.class, DataModule.class})
public interface ActivityComponent {

    FragmentComponent addFragmentComponent(FragmentMoudule fragmentMoudule);

    void inject(BaseMvpActivity baseMvpActivity);
}
