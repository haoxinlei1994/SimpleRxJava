package com.mrh.rxjava;

/**
 * 被观察者
 * Created by haoxinlei on 2020/6/15.
 */
public abstract class Observable<T> {

    public abstract void subscribe(Observer<T> observer);

    public <R> Observable<R> map(final Func<T, R> func) {
        final Observable source = this;
        return new Observable<R>() {
            @Override
            public void subscribe(final Observer<R> observer) {
                source.subscribe(new Observer<T>() {
                    @Override
                    public void onResult(T t) {
                        R result = func.call(t);
                        observer.onResult(result);
                    }

                    @Override
                    public void onError(Exception e) {
                        observer.onError(e);
                    }
                });
            }
        };
    }

    public <R> Observable<R> flatMap(final Func<T, Observable<R>> func) {
        final Observable source = this;
        return new Observable<R>() {
            @Override
            public void subscribe(final Observer<R> observer) {
                source.subscribe(new Observer<T>() {

                    @Override
                    public void onResult(T t) {
                        Observable<R> temObserver = func.call(t);
                        temObserver.subscribe(new Observer<R>() {
                            @Override
                            public void onResult(R r) {
                                observer.onResult(r);
                            }

                            @Override
                            public void onError(Exception e) {
                                observer.onError(e);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        };
    }
}
