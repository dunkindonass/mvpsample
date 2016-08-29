package kr.co.mvp.dagger2.sample.mvp.views;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.dagger.module.FragmentMoudule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpFragment;
import kr.co.mvp.dagger2.sample.mvp.model.LocationInfo;
import kr.co.mvp.dagger2.sample.mvp.model.Place;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListPresenter;
import kr.co.mvp.dagger2.sample.mvp.presenter.TasteListView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 8454 on 2016-08-16.
 */

public class TasteListFragment extends BaseMvpFragment implements TasteListView {


    @Inject
    Resources resources;

    @Inject
    TasteListPresenter githubListPresenter;

    @Bind(R.id.recyler_view)
    RecyclerView recyclerView;
    @Bind(R.id.text)
    TextView numbertext;

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


    @OnClick(R.id.btn)
    public void click() {

        Random random = new Random();
        Observable<Integer> integerObservable = Observable.create(subscriber -> subscriber.onNext(random.nextInt(46) + 1));


        Subscription subscription = new CompositeSubscription();

        HashMap<Integer, Integer> data = new HashMap<>();

        //Observable.interval(500, TimeUnit.MICROSECONDS).flatMap(aLong -> Observable.just(random.nextInt(46))).subscribe(integer -> System.out.println(integer));
        PublishSubject<HashMap<Integer, Integer>> publishSubject = PublishSubject.create();
        Observable<Integer> observableObservable = Observable.create(subscriber -> {
            subscriber.onNext(random.nextInt(46) + 1);
            subscriber.onCompleted();
        });
        observableObservable.subscribeOn(Schedulers.io()).repeat(10000).compose(injectProgress()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

                publishSubject.onNext(data);
                System.out.println("data print" + data);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                if (data.containsKey(integer)) {
                    data.put(integer, data.get(integer) + 1);
                } else {
                    data.put(integer, 1);
                }
            }
        });


        publishSubject.subscribe(integerIntegerHashMap -> {
            List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(integerIntegerHashMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            ArrayList<Integer> result = new ArrayList<Integer>();
            for (Map.Entry<Integer, Integer> entry : list) {
                result.add(entry.getKey());
            }

            Observable.from(result).take(6).toList().observeOn(AndroidSchedulers.mainThread()).subscribe(integers ->
                    numbertext.append("\n"+new Gson().toJson(integers)));


        });


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
