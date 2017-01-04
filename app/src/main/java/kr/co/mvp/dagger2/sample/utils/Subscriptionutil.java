package kr.co.mvp.dagger2.sample.utils;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by imcheol-u on 2017. 1. 2..
 */

public class Subscriptionutil {

    public static <T>Observable.Operator<T,T> composite(CompositeSubscription subscription){
        return subscriber -> {
            subscription.add(subscriber);
            subscriber.add(Subscriptions.create(()->subscription.remove(subscriber)));
            return subscriber;
        };
    }
}
