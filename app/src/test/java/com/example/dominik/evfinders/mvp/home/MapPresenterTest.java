package com.example.dominik.evfinders.mvp.home;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IEventsRepository;
import com.example.dominik.evfinders.model.base.home.MockEventsRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Dominik on 01.09.2017.
 */
public class MapPresenterTest {

    private MapContract.View view;
    private IEventsRepository repository;
    private MapPresenter presenter;
    private List<Event> mockedList = new ArrayList<>();

    @Before
    public void setUp(){
        repository = mock(MockEventsRepository.class);
        view = mock(MainActivity.class);
        presenter = new MapPresenter(repository);
        presenter.attach(view);
    }

    @Test
    public void on_view_create() throws Exception {

        presenter.attach(view);

        assertNotNull(view);
    }

    @Test
    public void detach() throws Exception {
    }

    @Test
    public void getEvents() throws Exception {
    }

}