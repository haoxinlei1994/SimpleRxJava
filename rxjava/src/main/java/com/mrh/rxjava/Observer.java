package com.mrh.rxjava;

/**
 * 观察者
 * Created by haoxinlei on 2020/6/15.
 */
public interface Observer<T> {
    void onResult(T t);

    void onError(Exception e);
}
