package com.example.dominik.evfinders.base;

import com.example.dominik.evfinders.mvp.model.Event;
import com.example.dominik.evfinders.mvp.view.BaseView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    protected void subscription(Single<List<Event>> observable, SingleObserver<List<Event>> observer){
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
