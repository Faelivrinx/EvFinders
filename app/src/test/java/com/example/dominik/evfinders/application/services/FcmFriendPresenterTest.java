package com.example.dominik.evfinders.application.services;

import com.example.dominik.evfinders.model.base.home.friends.IFriendsRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by Dominik on 17.10.2017.
 */
public class FcmFriendPresenterTest {

    @Mock
    FcmFriendContract.View view;

    @Mock
    IFriendsRepository repository;

    private FcmFriendPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        presenter = new FcmFriendPresenter(repository);
        presenter.attach(view);

    }

    @Test
    public void onAttach_shouldViewNotNull() {

        presenter.attach(view);

        Assert.assertNotNull(presenter.getView());
    }

    @Test
    public void onDetach_shouldViewBeNull() {

        presenter.detach();

        Assert.assertNull(presenter.getView());
    }

}