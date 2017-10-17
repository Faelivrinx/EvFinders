package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IEventsRepository;
import com.example.dominik.evfinders.model.base.home.MockEventsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static io.reactivex.Observable.fromArray;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dominik on 01.09.2017.
 */
public class MapPresenterTest {

    private MapContract.View view;
    private IEventsRepository repository;
    private MapPresenter presenter;


    private Single<List<Event>> emitEvents;
    private List<Event> zeroElementsList = new ArrayList<>();
    private List<Event> fiveElementsList = new ArrayList<>();

    @Before
    public void setUp() {
        repository = mock(MockEventsRepository.class);
        view = mock(MainActivity.class);
        presenter = new MapPresenter(repository);
        presenter.attach(view);
        createEvents();
    }

    private void createEvents() {
        for (int i = 0; i < 5; i++) {
            Event event = new Event();
            fiveElementsList.add(event);
        }
    }

    @Test
    public void onAttach_shouldViewNotNull() throws Exception {

        presenter.attach(view);

        assertNotNull(presenter.getView());
    }

    @Test
    public void onDetach_viewShouldBeNull() throws Exception {

        presenter.detach();

        Assert.assertNull(presenter.getView());
    }

    @Test
    public void whenGetEvents_thenUseRepository() throws Exception {
        emitEvents = Single.fromObservable(fromArray(zeroElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(repository, times(1)).getEvents();
    }

    @Test
    public void whenGetEvents_thenViewShowProgressBar() {
        emitEvents = Single.fromObservable(fromArray(zeroElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(view, times(1)).showProgressBar();
    }

    @Test
    public void whenGetEvents_thenViewHideProgressBar() {
        emitEvents = Single.fromObservable(fromArray(fiveElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(view, times(1)).hideProgressBar();
    }

}