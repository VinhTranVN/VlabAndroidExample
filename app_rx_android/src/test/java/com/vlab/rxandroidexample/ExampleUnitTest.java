package com.vlab.rxandroidexample;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testShareReplay() throws Exception {
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                System.out.println("Call API");
//                subscriber.onNext("API RESULT: " + System.currentTimeMillis());
//                subscriber.onCompleted();
//            }
//        }).share();
        CountDownLatch latch = new CountDownLatch(1);
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).publish().autoConnect();

        observable.subscribe(s1 -> System.out.println("Subscriber 1: " + s1));
        Thread.sleep(3000);
        observable.subscribe(s2 -> System.out.println("Subscriber 2: " + s2));
        observable.subscribe(s2 -> System.out.println("Subscriber 3: " + s2));
        observable.subscribe(s2 -> System.out.println("Subscriber 4: " + s2));

        latch.await();
    }

    @Test
    public void testName() throws Exception {
        ViewModel model = new ViewModel();

        // bind model
        Subscription s1 = model.onLoad().subscribe(s -> System.out.println("Result 1: " + s));
        model.load();

        // simulate screen orientation changed
        s1.unsubscribe();
        Thread.sleep(3000);

        // bind model
        Subscription s2 = model.onLoad().subscribe(s -> System.out.println("Result 2: " + s));

        model.load();
    }

    static class ViewModel {
        Observable<String> mOnLoad;
//        Observable<String> mOnLoad;

        PublishSubject<String> mCommand = PublishSubject.create();

        public ViewModel() {
            mOnLoad = mCommand
                    .first()
                    .flatMap(cmd -> callApi())
                    .replay(1).autoConnect();
        }

        private Observable<String> callApi() {
            return Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    System.out.println("Call API");
                    subscriber.onNext("HELLO: " + System.currentTimeMillis());
                    subscriber.onCompleted();
                }
            });
        }

        void load() {
            mCommand.onNext("Load: " + System.currentTimeMillis());
        }

        public Observable<String> onLoad() {
            return mOnLoad;
        }
    }
}