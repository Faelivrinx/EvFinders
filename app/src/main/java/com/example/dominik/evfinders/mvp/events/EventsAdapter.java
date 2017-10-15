package com.example.dominik.evfinders.mvp.events;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.Event;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 15.10.2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Event> events;
    private LayoutInflater inflater;

    public EventsAdapter(LayoutInflater inflater) {
        events = new ArrayList<>();
        this.inflater = inflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (events != null) {
            viewHolder.populate(events.get(position));
        }
    }

    public void notifyDataChanged(List<Event> events) {
        this.events.clear();
        if (events != null) {
            this.events = events;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_event_title)
        TextView tvTitle;

        @BindView(R.id.list_item_event_description)
        TextView tvDescription;

        @BindView(R.id.list_item_event_countFriends)
        TextView tvCountFriends;

        @BindView(R.id.list_item_event_btnComment)
        Button btnComment;

        @BindView(R.id.list_item_event_tvComment)
        TextView tvComment;

        @BindView(R.id.list_item_event_cardView)
        CardView mainView;

        @BindView(R.id.list_item_event_btnAttend)
        Button btnAttend;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(Event event) {
            switch (event.getEventType()){
                case SPORT_AND_RECREATION:
                    mainView.setCardBackgroundColor(0xC58ade9e);
                    break;
                case MUSIC:
                    break;
                case CINEMA:
                    mainView.setCardBackgroundColor(0xC5a4e9e7);
                    break;
                default:
                    break;
            }
            tvTitle.setText(event.getName());
            tvDescription.setText(event.getDescription());
            tvCountFriends.setText(String.valueOf(event.getUsersRegisteredToEvent().size()));
            tvComment.setText(String.valueOf(event.getUsersRegisteredToEvent().size()));
        }

    }
}
