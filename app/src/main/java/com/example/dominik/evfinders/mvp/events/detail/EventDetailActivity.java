package com.example.dominik.evfinders.mvp.events.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.base.BaseAuthActivity;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.database.pojo.Event;

import java.sql.Date;

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


    @BindView(R.id.activity_events_event_comments)
    RecyclerView recyclerView;

    private CommentsAdapter commentsAdapter;


    @Inject
    EventDetailContract.Presenter presenter;


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
    }

    @OnClick(R.id.activity_events_event_show_comments)
    public void onShowEventsClick(){
        detailLayout.setVisibility(View.GONE);
        layoutComments.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.activity_events_event_comment_back)
    public void onBackToEventClicked(){
        detailLayout.setVisibility(View.VISIBLE);
        layoutComments.setVisibility(View.GONE);
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

        tvTitle.setText(event.getName());
        tvPlace.setText(event.getAddress());
        tvDate.setText(new Date(event.getDate()).toString());
        tvDescription.setText(event.getDescription());
    }
}
