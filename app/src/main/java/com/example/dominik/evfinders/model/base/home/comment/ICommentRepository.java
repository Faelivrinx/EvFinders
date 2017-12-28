package com.example.dominik.evfinders.model.base.home.comment;

import com.example.dominik.evfinders.command.CommentCommand;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by Dominik on 27.12.2017.
 */

public interface ICommentRepository {
    Single<Response<CommentCommand>> addComment(CommentCommand commentCommand);
}
