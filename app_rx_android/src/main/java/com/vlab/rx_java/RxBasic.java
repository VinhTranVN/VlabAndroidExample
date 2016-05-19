package com.vlab.rx_java;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Vinh.Tran on 5/19/16.
 * Basic concept:
 * - Observable emits items
 * - Subscriber consumes those items
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

    public static void main(String[] args) {
        ////////////////////////////////////
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext(TAG + " >>> onNext ");
                subscriber.onNext(TAG + " >>> onNext 1");
                subscriber.onNext(TAG + " >>> onNext 2");

                String nullString = null;
                //System.out.println(TAG + " >>> make an null pointer error ");
                //System.out.println(nullString.toString());

                subscriber.onCompleted();
                //subscriber.onNext(TAG + " >>> not call after complete onNext 3");
            }
        });
        stringObservable.subscribe(firstSubscriber);


        System.out.println(TAG + " >>> main : firstSubscriber.isUnsubscribed() "+ firstSubscriber.isUnsubscribed());
        /*firstSubscriber.onNext(" next event ");
        stringObservable.subscribe();*/


        //-------------------- map operator
        System.out.println("--------------------");
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
        System.out.println("--------------------");
        Observable<String> observable2 = Observable.just("12345");
        observable2.doOnNext(str -> System.out.println("string is " + str))
                    .map(str -> str.length())
                    .subscribe(secondSubscriber);


    }
}
