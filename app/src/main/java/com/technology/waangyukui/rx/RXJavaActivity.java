package com.technology.waangyukui.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.technology.waangyukui.mycyclerapp.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lenvo on 2018/6/25.
 */

public class RXJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        Flowable flowable = Flowable.create(new FlowableOnSubscribe() {
            @Override
            public void subscribe(FlowableEmitter emitter) throws Exception {

                emitter.onNext(1);
                Log.i("RXJavaActivity", "emitter 1");
                emitter.onNext(2);
                Log.i("RXJavaActivity", "emitter 2");
                emitter.onNext(3);
                Log.i("RXJavaActivity", "emitter 3");
                emitter.onNext(4);
                Log.i("RXJavaActivity", "emitter 4");

            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                Log.i("RXJavaActivity", "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.i("RXJavaActivity","onNext :"+integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.i("RXJavaActivity","onError :"+t);
            }

            @Override
            public void onComplete() {
                Log.i("RXJavaActivity","onComplete :");
            }

        };

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

}
