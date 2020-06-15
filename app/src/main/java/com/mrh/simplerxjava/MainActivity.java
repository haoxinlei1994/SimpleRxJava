package com.mrh.simplerxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.mrh.rxjava.Func;
import com.mrh.rxjava.Observable;
import com.mrh.rxjava.Observer;

import java.util.List;

/**
 * 需求：
 * 1、请求所有网络图片
 * 2、筛选url包含  image/1  的数据
 * 3、将url存到本地
 */
public class MainActivity extends AppCompatActivity {

    private ImagesRepo mImagesRepo = new ImagesRepo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImagesRepo.fetchImagesUrl("animal")
                .map(new Func<List<String>, String>() {
                    @Override
                    public String call(List<String> strings) {
                        for (String url : strings) {
                            if (url.contains("image/1")) {
                                return url;
                            }
                        }
                        return null;
                    }
                })
                .flatMap(new Func<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return mImagesRepo.store(s);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onResult(String url) {
                        Log.d("mrh", "url 存储成功");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("mrh", "url 存储失败" + e);
                    }
                });
    }
}
