package com.example.dominik.evfinders.base;

import com.example.dominik.evfinders.mvp.view.BaseView;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dominik on 22.06.2017.
 * @author Dominik
 * @since 1.0
 */

public abstract class BasePresenter <V extends BaseView> {

    private V view;

    public V getView() {
        return view;
    }

    protected void subscription(Observable<V> observable, Observer<V> observer){
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
