package com.example.dominik.evfinders.mvp.events.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.command.CommentCommand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 05.12.2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;

    public CommentsAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    private List<CommentCommand> comments = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void notifyDataChanged(List<CommentCommand> newComments) {
        if (newComments != null) {
            comments.clear();
            comments.addAll(newComments);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_comment_comment)
        TextView tvComment;

        @BindView(R.id.list_item_comment_username)
        TextView tvUsername;

        @BindView(R.id.list_item_comment_rating_bar)
        RatingBar rbRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void populate(String username, String comment, int rating){
            tvComment.setText(comment);
            tvUsername.setText(username);
            rbRating.setRating(rating);
        }

        protected void populate(CommentCommand commentCommand) {
            tvComment.setText(commentCommand.getComment());
            tvUsername.setText(commentCommand.getAuthor());
            rbRating.setRating(commentCommand.getRating());
            rbRating.setIsIndicator(true);
        }
    }
}
