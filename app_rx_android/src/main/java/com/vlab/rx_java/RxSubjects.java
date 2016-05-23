package com.vlab.rx_java;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by Vinh.Tran on 5/23/16.
 */
public class RxSubjects {

    private static final String TAG = "RxSubjects";

    private static Action1<String> subscriber1 = s -> System.out.println(" >>> testPublicSubject subscriber 1 : " + s);
    private static Action1<String> subscriber2 = s -> System.out.println(" >>> testPublicSubject subscriber 2 : " + s);

    public static void main(String[] args) {
        testPublicSubject();
        //testBehaviorSubject();
    }

    private static void testPublicSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        // observer1 will receive all onNext and onCompleted events
        subject.subscribe(subscriber1);
        subject.onNext("one");
        subject.onNext("two");
        // observer2 will only receive "three" and onCompleted
        subject.subscribe(subscriber2);
        subject.onNext("three");
        subject.onCompleted();
    }


    /**
     * http://reactivex.io/RxJava/javadoc/rx/subjects/BehaviorSubject.html
     */
    private static void testBehaviorSubject() {
        System.out.println(" >>> --------------------- testBehaviorSubject ");
        // observer will receive all events.
        BehaviorSubject<String> subject = BehaviorSubject.create("default");
        subject.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(" >>> subject onCompleted : ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(" >>> subject onNext : " + s);
            }
        });
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

        System.out.println("---------------------");

        // observer will receive the "one", "two" and "three" events, but not "zero"
        BehaviorSubject<String> subject1 = BehaviorSubject.create("default");
        subject1.onNext("zero");
        subject1.onNext("one");
        subject1.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(" >>> subject1 onCompleted : ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(" >>> subject1 onNext : " + s);
            }
        });
        subject1.onNext("two");
        subject1.onNext("three");

        System.out.println("---------------------");

        // observer will receive only onCompleted
        BehaviorSubject<String> subject2 = BehaviorSubject.create("default");
        subject2.onNext("zero");
        subject2.onNext("one");
        subject2.onCompleted();
        subject2.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(" >>> subject2 onCompleted ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(" >>> subject2 onNext : " + s);
            }
        });

        System.out.println("---------------------");

        // observer will receive only onError
        BehaviorSubject<String> subject3 = BehaviorSubject.create("default");
        subject3.onNext("zero");
        subject3.onNext("one");
        subject3.onError(new RuntimeException("error"));
        subject3.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(" >>> subject3 onCompleted : ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" >>> subject3 onError : " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println(" >>> subject3 onNext : " + s);
            }
        });
    }

}
