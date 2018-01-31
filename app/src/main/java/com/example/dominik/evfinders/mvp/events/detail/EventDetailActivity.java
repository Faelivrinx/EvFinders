package com.example.dominik.evfinders.mvp.events.detail;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.CommentCommand;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

import static com.example.dominik.evfinders.mvp.events.EventsActivity.CHOOSE_EVENT;

/**
 * Created by Dominik on 27.10.2017.
 */

public class EventDetailActivity extends BaseAuthActivity implements EventDetailContract.View {

    @BindView(R.id.list_item_event_title)
    TextView tvTitle;

    @BindView(R.id.activity_events_event_place)
    TextView tvPlace;

    @BindView(R.id.activity_events_event_date)
    TextView tvDate;

    @BindView(R.id.list_item_event_description)
    TextView tvDescription;

    @BindView(R.id.activity_events_event_friends)
    TextView tvFriendsCount;

    @BindView(R.id.activity_events_event_progressBar)
    ProgressBar progressBar;

    @BindView(R.id.activity_events_event_commentsLayout)
    LinearLayout layoutComments;

    @BindView(R.id.activity_events_event_mainLayout)
    ConstraintLayout mainLayout;

    @BindView(R.id.activity_events_event_detailLayout)
    ConstraintLayout detailLayout;

    @BindView(R.id.activity_events_event_addComment)
    FloatingActionButton fabAddComment;


    @BindView(R.id.activity_events_event_comments)
    RecyclerView recyclerView;

   private EventCommand eventCommand;

    @Inject
    EventDetailContract.Presenter presenter;

    private CommentsAdapter commentsAdapter;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private EditText etDialogComment;
    private Button btnDialogAccept;
    private Button btnDialogCancel;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter.attach(this);
        commentsAdapter = new CommentsAdapter(getLayoutInflater());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentsAdapter);
        getEvent();
        createAlertDialog();

        fabAddComment.setVisibility(View.GONE);


        fabAddComment.setOnClickListener(view -> {
            alertDialog.show();
        });
    }

    @OnClick(R.id.activity_events_event_show_comments)
    public void onShowEventsClick(){
        detailLayout.setVisibility(View.GONE);
        layoutComments.setVisibility(View.VISIBLE);
        fabAddComment.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.activity_events_event_comment_back)
    public void onBackToEventClicked(){
        detailLayout.setVisibility(View.VISIBLE);
        layoutComments.setVisibility(View.GONE);
        fabAddComment.setVisibility(View.GONE);
    }

    @OnClick(R.id.activity_events_event_btnAttend)
    public void onAttendButtonClick(){
        presenter.attend(eventCommand.getId());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_events_event;
    }

    @Override
    protected int getNavItem() {
        return 0;
    }

    @Override
    public void getEvent() {
        EventCommand currentEvent = getIntent().getParcelableExtra(CHOOSE_EVENT);
        presenter.checkEvent(currentEvent);
    }

    @Override
    public void showEvent(EventCommand event) {
        this.eventCommand = event;
        commentsAdapter.notifyDataChanged(event.getCommentCommands());
        fillData(event);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackButtonClicked() {

    }

    @Override
    public void onShowCommentsButtonClicked() {

    }

    @Override
    public void onFriendsButtonClicked() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateComment(CommentCommand comment) {
        List<CommentCommand> currentComments = new ArrayList<>();
        currentComments.addAll(eventCommand.getCommentCommands());
        currentComments.add(comment);
        commentsAdapter.notifyDataChanged(currentComments);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    private void fillData(EventCommand event) {
        switch (event.getEventType()){
            case SPORT_AND_RECREATION:
                mainLayout.setBackgroundColor(0xC58ade9e);
                break;
            case MUSIC:
                mainLayout.setBackgroundColor(0xDEffdbc5);
                break;
            case CINEMA:
                mainLayout.setBackgroundColor(0xC5a4e9e7);
                break;
            default:
                break;
        }
        tvFriendsCount.setText(String.valueOf(event.getUsers().size()));
        tvTitle.setText(event.getName());
        tvPlace.setText(event.getAddress());
//        tvDate.setText(new Date(event.getDate()).toString());
        tvDate.setText(android.text.format.DateFormat.format("dd/MM/yyyy HH:mm", new java.util.Date(event.getDate())).toString());
        tvDescription.setText(event.getDescription());
    }

    private void createAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_comment, null);
        alertDialogBuilder.setView(dialogView);
        alertDialog = alertDialogBuilder.create();
        etDialogComment = dialogView.findViewById(R.id.dialog_add_comment_comment);
        btnDialogAccept = dialogView.findViewById(R.id.dialog_add_comment_btnAdd);
        btnDialogCancel = dialogView.findViewById(R.id.dialog_add_comment_btnCancel);
        ratingBar = dialogView.findViewById(R.id.dialog_add_comment_rating);

        btnDialogAccept.setOnClickListener(view -> {
            if (!etDialogComment.getText().toString().equals(""))
            presenter.addComment(etDialogComment.getText().toString(), eventCommand.getId(), (int)ratingBar.getRating());
            etDialogComment.setText("");
            alertDialog.dismiss();
        });
        btnDialogCancel.setOnClickListener(view -> {
            alertDialog.dismiss();
        });
    }
}
