package com.example.dominik.evfinders.mvp.login;

import com.example.dominik.evfinders.database.pojo.Event;
import com.example.dominik.evfinders.database.pojo.network.ApiKeyResponse;
import com.example.dominik.evfinders.model.base.home.login.ILoginRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dominik on 17.10.2017.
 */
public class LoginPresenterTest {

    @Mock
    LoginContract.View view;

    @Mock
    ILoginRepository repository;

    private LoginPresenter presenter;

    // Mock data
    private Observable<ApiKeyResponse> emitApiKey;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new LoginPresenter(repository);
        presenter.attach(view);

    }

    @Test
    public void whenLogin_thenSaveKey(){
        ApiKeyResponse response = new ApiKeyResponse();
        response.setName("api_key");
        response.setValue("fcm_token");

        presenter.onNext(response);

        verify(repository, times(1)).saveKey(response);
    }

    @Test
    public void whenUsernameNull_thenReturnFalse(){
        boolean result = presenter.validateData("", "password");

        Assert.assertFalse(result);
    }

    @Test
    public void whenPasswordNull_thenReturnFalse(){
        boolean result = presenter.validateData("test", "");

        Assert.assertFalse(result);
    }

    @Test
    public void whenDataNotNull_thenReturnTrue(){
        boolean password = presenter.validateData("username", "password");

        Assert.assertTrue(password);
    }

    @Test
    public void onError_thenHideProgressBarAndShowToast(){

        presenter.onError(new Exception("Error"));

        verify(view, times(1)).hideProgressDialog();
        verify(view, times(1)).showToast("Can't login");
    }

    @Test
    public void onComplete(){
        presenter.onComplete();

        verify(view, times(1)).startActivity();
        verify(view, times(1)).hideProgressDialog();
    }
}