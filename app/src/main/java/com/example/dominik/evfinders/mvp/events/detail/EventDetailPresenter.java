package com.example.dominik.evfinders.mvp.events.detail;

import android.util.Log;

import com.example.dominik.evfinders.command.CommentCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.model.base.home.comment.ICommentRepository;
import com.example.dominik.evfinders.model.base.home.event.IEventsRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dominik on 27.10.2017.
 */

public class EventDetailPresenter implements EventDetailContract.Presenter {

    private EventDetailContract.View view;
    private ICommentRepository commentRepository;
    private IEventsRepository eventsRepository;

    @Inject
    public EventDetailPresenter(ICommentRepository commentRepository, IEventsRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.eventsRepository = eventRepository;
    }

    @Override
    public void attach(EventDetailContract.View view) {
        if (this.view == null){
            this.view = view;
        }
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void checkEvent(EventCommand event) {
        view.showProgressBar();
        if (event != null){
            view.showEvent(event);
            view.hideProgressBar();
        }
    }

    @Override
    public void addComment(String comment, Long eventCommandId, int numStars) {
        view.showProgressBar();
        CommentCommand commentCommand = createEventCommant(comment, eventCommandId, numStars);
        Single<Response<CommentCommand>> comments = commentRepository.addComment(commentCommand);
        comments.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkResponseComment, throwable -> {
                    view.hideProgressBar();
                });

    }

    @Override
    public void attend(Long id) {
        Single<Response<EventCommand>> responseSingle = eventsRepository.attendAtEvent(id);
        responseSingle
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::checkResponseEvent, throwable -> {
                    Log.d("DETAIL CLASS",  "attend: " + throwable.getMessage());
                });
    }

    private CommentCommand createEventCommant(String comment, Long eventCommandId, int stars) {
        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setComment(comment);
        commentCommand.setEventId(eventCommandId);
        commentCommand.setRating(stars);
        return commentCommand;
    }

    private void checkResponseComment(Response<CommentCommand> response){
        view.hideProgressBar();
        if (response.code() == 200 && response.body().getId() != null){
            view.showMessage("Komentarz został dodany");
        } else if(response.code() == 200 && response.body().getId() == null){
            view.showMessage("Już to komentowałeś!");
        } else {
            view.showMessage("Coś poszło nie tak!");
        }
    }

    private void checkResponseEvent(Response<EventCommand> response){
        view.hideProgressBar();
        if (response.code() == 200 && response.body().getId() != null){
            view.showMessage("Uczęszczasz na to wydarzenie!");
        } else if(response.code() == 200 && response.body().getId() == null){
            view.showMessage("Uczeszczasz już na to wydarzenie!");
        } else {
            view.showMessage("Coś poszło nie tak!");
        }
    }
}
