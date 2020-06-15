package com.mrh.simplerxjava;

import android.text.TextUtils;

import com.mrh.rxjava.Observable;
import com.mrh.rxjava.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoxinlei on 2020/6/15.
 */
public class ImagesRepo {

    public Observable<List<String>> fetchImagesUrl(String type) {
        return new Observable<List<String>>() {
            @Override
            public void subscribe(Observer<List<String>> observer) {
                //模拟请求数据成功
                List<String> result = fetchDataFromServer();
                observer.onResult(result);
            }
        };
    }

    private List<String> fetchDataFromServer() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("http://image/" + i);
        }
        return data;
    }

    public Observable<String> store(final String url) {
        return new Observable<String>() {
            @Override
            public void subscribe(Observer<String> observer) {
                if (TextUtils.isEmpty(url)) {
                    observer.onError(new IllegalStateException("url can not be null"));
                } else {
                    //模拟存储到本地，耗时操作
                    observer.onResult(url);
                }
            }
        };
    }
}
