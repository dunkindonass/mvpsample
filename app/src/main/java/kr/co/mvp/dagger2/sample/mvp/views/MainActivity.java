package kr.co.mvp.dagger2.sample.mvp.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.mvp.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.mvp.model.SearchItems;
import kr.co.mvp.dagger2.sample.mvp.presentor.GitHubListView;
import kr.co.mvp.dagger2.sample.mvp.presentor.GithubListPresenter;

public class MainActivity extends BaseMvpActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        onCallFragment(new TasteListFragment(),ROOTFRAGMENT, null);

    }

    public void onInjectMainActivity() {
        ((ApplicationComponent) SampleApplication.getApplicationComponent()).addActivityComponent(new ActivityModoule(this)).inject(this);
    }


}
