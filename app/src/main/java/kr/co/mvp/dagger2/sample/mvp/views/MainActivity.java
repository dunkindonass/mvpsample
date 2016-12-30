package kr.co.mvp.dagger2.sample.mvp.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.mvp.dagger2.sample.R;
import kr.co.mvp.dagger2.sample.SampleApplication;
import kr.co.mvp.dagger2.sample.dagger.component.ApplicationComponent;
import kr.co.mvp.dagger2.sample.dagger.module.ActivityModoule;
import kr.co.mvp.dagger2.sample.mvp.base.BaseMvpActivity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseMvpActivity {


    @Bind(R.id.container_layout)
    FrameLayout containerLayout;
    @Bind(R.id.btn01)
    Button btn01;
    @Bind(R.id.btn02)
    Button btn02;
    @Bind(R.id.btn03)
    Button btn03;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
        getDate();
    }

    public void init() {
        onCallFragment(new TasteListFragment(), ROOTFRAGMENT, null);
    }


    public void getDate() {
        long currentDate = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("F");
        String result = simpleDateFormat.format(new Date(currentDate));

    }

    public void rxLimitTest() {
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
        observableObservable.subscribeOn(Schedulers.io()).repeat(10000).subscribe(new Subscriber<Integer>() {
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

            Observable.from(result).take(6).toList().subscribe(integers -> System.out.println(integers));


        });

    }


    public void testBehavierSubject01() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.filter(text -> !text.equals("aaa")).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }


}
