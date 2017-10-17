package com.example.dominik.evfinders.mvp.events;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.model.base.home.IEventsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static io.reactivex.Observable.fromArray;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dominik on 17.10.2017.
 */
public class EventsPresenterTest {

    @Mock
    EventsContract.View view;

    @Mock
    IEventsRepository repository;

    private EventsPresenter presenter;

    // Mock data
    private Single<List<Event>> emitEvents;
    private List<Event> zeroElementsList = new ArrayList<>();
    private List<Event> fiveElementsList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new EventsPresenter(repository);
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
    public void whenGetEvents_thenUseRepository(){
        emitEvents = Single.fromObservable(fromArray(zeroElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(repository, times(1)).getEvents();
    }

    @Test
    public void whenGetEvents_shouldEmitEventsToView(){
        emitEvents = Single.fromObservable(fromArray(fiveElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(view, times(1)).showEvents(fiveElementsList);
    }

    @Test
    public void whenEventsListSizeIsZero_shouldShowEmptyEventList(){
        emitEvents = Single.fromObservable(fromArray(zeroElementsList));
        when(repository.getEvents()).thenReturn(emitEvents);

        presenter.getEvents();

        verify(view, times(1)).showEvents(zeroElementsList);
    }


    @Test(expected = NullPointerException.class)
    public void whenRepositoryReturnNullObject_expectedNullException(){
        when(repository.getEvents()).thenReturn(null);

        presenter.getEvents();
    }

    @Test
    public void onDetach_viewShouldBeNull(){

        presenter.detach();

        Assert.assertNull(presenter.getView());
    }
}