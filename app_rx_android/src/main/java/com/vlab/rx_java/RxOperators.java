package com.vlab.rx_java;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by Vinh.Tran on 5/20/16.
 * Refer : http://blog.danlew.net/2014/09/22/grokking-rxjava-part-2/
 */
public class RxOperators {

    private static String TAG = "RxOperators";
    private static final List<String> MAP_URLS = new ArrayList<>();

    static {
        MAP_URLS.add("www.Hello1.com/title_1");
        MAP_URLS.add("www.World2.com/title_2");
        MAP_URLS.add("www.Hello3.com/title_3");
        MAP_URLS.add("www.World4.com/title_4");
        MAP_URLS.add("www.Hello5.com/_");
    }

    public static void main(String[] args) {

        /*String searchText = "Hello";

        method1(searchText);

        method2(searchText);

        method3(searchText);*/

        PublishSubject<String> subject = PublishSubject.create();
        Observable<String> obs1 = subject.asObservable()
                .doOnCompleted(() -> System.out.println("doOnCompleted"));

        obs1.subscribe(str -> System.out.println("1 " + str));

//        obs1.subscribe(str -> System.out.println("2 " + str));
//
//        obs1.subscribe(str -> System.out.println("3 " + str));

        subject.onNext("text");
    }

    private static void testError(){
        PublishSubject<String> subject = PublishSubject.create();
        Observable<String> obs1 = subject.asObservable()
                .doOnNext(str -> {
                    System.out.println(">>> doOnNext aaa");
                    throw new RuntimeException("RuntimeException");
                })
                .onErrorResumeNext(Observable.empty())
                .onErrorReturn(throwable -> {
                    System.out.println(">>> onErrorReturn " + throwable.getMessage());
                    return "return";
                })
                .doOnError(str -> System.out.println(">>> doOnError " + str))
                .doOnNext(str -> System.out.println(">>> doOnNext ccc " + str))
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted");
                    }
                });

        obs1.subscribe(str -> System.out.println("1 " + str));
    }

    private static void method1(String text) {
        query(text)
                .subscribe(urls -> {

                    for (String str : urls) {
                        System.out.println(TAG + " >>> method1 : " + str);
                    }

                });
    }

    private static void method2(String text) {
        System.out.println("------------------ Observable.from ");
        query(text).subscribe(urls -> {

            Observable
                    .from(urls)
                    .subscribe(url -> {
                        System.out.println(TAG + " >>> method2 call : " + url);
                    });

        });
    }

    private static void method3(String text) {
        System.out.println("------------------ Observable.from flatmap ");
        query(text)
                .flatMap(urls -> Observable.from(urls))
                .flatMap(url -> getTitle(url))
                .filter(title -> !"_".equals(title))
                .subscribe(
                        title -> System.out.println("Title is " + title),
                        error -> System.out.println("error is " + error.toString())
                );
    }

    /**
     * Returns a List of website URLs based on a text search
     */
    private static Observable<List<String>> query(String text) {

        /*Observable<List<String>> listObservable = Observable
                .just(MAP_URLS)
                .map(urls -> {
                    List<String> urlsContainText = new ArrayList<String>();
                    for (String url : urls) {
                        if (url.contains(text)) {
                            urlsContainText.add(url);
                        }
                    }
                    return urlsContainText;
                });*/

        return Observable
                .from(MAP_URLS)
                .filter(url -> url.contains(text))
                .toList();
    }

    private static Observable<String> getTitle(String url) {
        return Observable
                .just(url)
                .map(s -> s.split("/")[1]);// get title from url string
    }

}
