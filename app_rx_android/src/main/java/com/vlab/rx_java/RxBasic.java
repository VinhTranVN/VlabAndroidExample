package com.vlab.rx_java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Vinh.Tran on 5/19/16.
 * Basic concept:
 * - Observable emits items
 * - Subscriber consumes those items
 *
 * refer : http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/
 */
public class RxBasic {

    private static final String TAG = "RxBasic";

    private static final Subscriber<String> firstSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println(TAG + " >>> firstSubscriber onCompleted : ");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println(TAG + " >>> firstSubscriber onError : " + e.getMessage());
        }

        @Override
        public void onNext(String s) {
            System.out.println(TAG + " >>> firstSubscriber onNext : " + s);
        }
    };

    private static final Subscriber<Integer> secondSubscriber = new Subscriber<Integer>() {
        @Override
        public void onCompleted() {
            System.out.println(TAG + " secondSubscriber onCompleted : ");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println(TAG + " secondSubscriber onError : " + e.getMessage());
        }

        @Override
        public void onNext(Integer integer) {
            System.out.println(TAG + " secondSubscriber onNext  : " + integer);
        }
    };
    private static String textDemo;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(100);
        Observable<Long> hot = Observable.interval(1, TimeUnit.SECONDS).share();
        hot.subscribe(i -> System.out.println("Subscriber 1: " + i));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hot.subscribe(i -> System.out.println("Subscriber 2: " + i));

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ////////////////////////////////////
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(TAG + " >>> onNext " );
                subscriber.onNext(TAG + " >>> onNext 1");
                subscriber.onNext(TAG + " >>> onNext 2");

                //String nullString = null;
                //System.out.println(TAG + " >>> make an null pointer error ");
                //System.out.println(nullString.toString());
                subscriber.onCompleted();
            }
        });
        stringObservable.subscribe(firstSubscriber);

        //-------------------- map operator
        System.out.println("-------------------- map operator ");
        Observable<String> observable1 = Observable.just("Hello ");
        observable1.map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " - append my name Vinh Tran by transform operator (map)";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String stringResult) {
                        System.out.println(TAG + " observable1 >>> call : " + stringResult);
                    }
                })
        ;

        //-------------------- map operator : transform string data to integer before emit data ro subscriber
        System.out.println("-------------------- map operator : transform string data to integer before emit data ro subscriber");
        Observable<String> observable2 = Observable.just("12345");
        observable2.doOnNext(str -> System.out.println("string is " + str))
                    .map(str -> str.length())
                    .subscribe(secondSubscriber);


        //-------------------- use defer when create observer for long task called before create Observer
        Observable<String> stringObservable1 = testJustMethod();
        /*stringObservable1
                .doOnNext(s -> System.out.println(TAG + " stringObservable1 >>> subscribe "))
                .subscribe(s -> System.out.println(TAG + " >>> stringObservable1 : "));*/

        Observable<String> stringObservable2 = testDeferMethod();
        /*stringObservable2
                .doOnNext(s -> System.out.println(TAG + " stringObservable2 >>> subscribe "))
                .subscribe(s -> System.out.println(TAG + " >>> stringObservable2 : "));*/

    }

    private static Observable<String> testJustMethod() {
        System.out.println("-------------------- Test Just");
        return Observable.just(getLongTask());
    }

    private static Observable<String> testDeferMethod() {
        System.out.println("-------------------- Test Defer");
        return Observable.defer(() -> Observable.just(getLongTask()));
    }

    public static String getLongTask() {
        System.out.println(TAG + " >>> call getLongTask to return a string ");
        return textDemo;
    }
}
