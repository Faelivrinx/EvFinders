package com.example.dominik.evfinders.model.base.home.comment;

import com.example.dominik.evfinders.command.CommentCommand;
import com.example.dominik.evfinders.model.api.CommentService;
import com.example.dominik.evfinders.model.repo.IPrefs;
import com.example.dominik.evfinders.model.repo.Prefs;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Dominik on 27.12.2017.
 */

public class CommentRepository implements ICommentRepository {

    private IPrefs prefs;
    private CommentService commentService;

    @Inject
    public CommentRepository(IPrefs prefs, CommentService commentService) {
        this.prefs = prefs;
        this.commentService = commentService;
    }

    @Override
    public Single<Response<CommentCommand>> addComment(CommentCommand commentCommand) {
        return commentService.addComment(prefs.get(Prefs.API_KEY), commentCommand);
    }

}
