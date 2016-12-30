package kr.co.mvp.dagger2.sample.dagger.component;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.app.Fragment;

import dagger.Component;
import dagger.Subcomponent;
import kr.co.mvp.dagger2.sample.dagger.PerActivity;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.DataModule;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.dagger.module.UserInfoMoudle;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.dagger.utils.ProgressDialogProvider;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.database.DataHelper;
import kr.co.mvp.dagger2.sample.mvp.views.MainActivity;
import kr.co.mvp.dagger2.sample.network.service.NetworkApi;

/**
 * Created by 8454 on 2016-08-09.
 */

@PerActivity
@Subcomponent(modules = {ActivityModoule.class, DataModule.class})
public interface ActivityComponent {

    FragmentComponent addFragmentComponent(FragmentMoudule fragmentMoudule);

    void inject(BaseMvpActivity baseMvpActivity);

    void inject(MainActivity mainActivity);
}
