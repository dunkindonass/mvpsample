package kr.co.mvp.dagger2.sample.mvp.views;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.dagger.utils.PreferenceUtil;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.views.fragment.GithubRepoFragment;

public class MainActivity extends BaseMvpActivity {

    @Inject
    DispatchingAndroidInjector<Fragment> activityDispatchingAndroidInjector;

    @Inject
    PreferenceUtil preferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    public void init() {
        onCallFragment(R.id.container_layout,new GithubRepoFragment(), ROOTFRAGMENT);
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return activityDispatchingAndroidInjector;
    }
}
