package kr.co.mvp.dagger2.sample.dagger.component;

import dagger.Component;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.module.ApplicationModule;
import kr.co.mvp.dagger2.sample.dagger.module.SampleTestMoudule;
import kr.co.mvp.dagger2.sample.mvp.views.MainActivity;

/**
 * Created by imcheol-u on 2017. 1. 5..
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class},modules = SampleTestMoudule.class)
public interface SampleTestComponent {

    void inject(MainActivity mainActivity);
}
