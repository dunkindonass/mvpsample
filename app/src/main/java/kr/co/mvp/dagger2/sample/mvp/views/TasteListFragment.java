package kr.co.mvp.dagger2.sample.mvp.views;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.mvp.presenter.GitHubListView;
import kr.co.mvp.dagger2.sample.mvp.presenter.GithubListPresenter;

/**
 * Created by 8454 on 2016-08-16.
 */

public class TasteListFragment extends BaseMvpFragment implements GitHubListView {


    @Inject
    Resources resources;

    @Inject
    GithubListPresenter githubListPresenter;

    @Bind(R.id.recyler_view)
    RecyclerView recyclerView;


    private ArrayList<Place> searchItems = new ArrayList<>();
    private int index = 1;
    private boolean Loading;
    private int visibleHold = 2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.tastelistfragment, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        onInjectTasteFragment();
        githubListPresenter.attach(this);
        githubListPresenter.requestGithubData(index);
    }

    public void onInjectTasteFragment() {
        ((ApplicationComponent) SampleApplication.getApplicationComponent()).addActivityComponent(new ActivityModoule(parentActivity)).addFragmentModule(new FragmentMoudule(this)).inject(this);
    }


    public void init() {
        recyclerView.setAdapter(new TasteRecyclerAdapter(parentActivity, searchItems));
        recyclerView.setLayoutManager(new LinearLayoutManager(parentActivity));
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        if (tasteRecyclerAdapter != null) {
            tasteRecyclerAdapter.setItemClickCallback(new TasteRecyclerAdapter.ItemClick() {
                @Override
                public void callWebView(String url) {
                    if (url == null || url.isEmpty()) {
                        Toast.makeText(parentActivity, resources.getString(R.string.weblink_blank), Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", url);
                        ((BaseMvpActivity) parentActivity).onCallFragment(new TasteWebFragment(), null, bundle);
                    }

                }
            });
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleCount = linearLayoutManager.findLastVisibleItemPosition();
                if (!Loading && totalItemCount <= (lastVisibleCount + visibleHold)) {
                    index++;
                    Loading = true;
                    tasteRequest();
                }
            }
        });
    }

    public void tasteRequest() {
        githubListPresenter.requestGithubData(index);
    }


    @Override
    public void showData(LocationInfo data) {
        Loading = false;
        searchItems.addAll(data.getSearchItems().getPlaces());
        TasteRecyclerAdapter tasteRecyclerAdapter = (TasteRecyclerAdapter) recyclerView.getAdapter();
        tasteRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected String getFragmentTag() {
        return "TasteListFragments";
    }
}
