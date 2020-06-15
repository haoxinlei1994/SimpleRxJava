package com.mrh.rxjava;

/**
 * Created by haoxinlei on 2020/6/15.
 */
public interface Func<T, R> {
    R call(T t);
}
