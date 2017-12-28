package com.example.dominik.evfinders.mvp.events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.command.EventCommand;
import com.example.dominik.evfinders.mvp.events.detail.EventDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.dominik.evfinders.mvp.events.EventsActivity.CHOOSE_EVENT;

/**
 * Created by Dominik on 15.10.2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EventCommand> events;

    private LayoutInflater inflater;
    private Context context;
    public EventsAdapter(LayoutInflater inflater, Context context) {
        events = new ArrayList<>();
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        holder.itemView.setOnClickListener((View view) -> {
            Intent intent = new Intent(context, EventDetailActivity.class);
            intent.putExtra(CHOOSE_EVENT, events.get(position));
            Pair<View, String> title = android.support.v4.util.Pair.create(((ViewHolder) holder).getTitle(), "title");
            Pair<View, String> description = Pair.create(((ViewHolder) holder).getDescription(), "description");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, title, description);
            context.startActivity(intent, options.toBundle());
        });
        if (events != null) {
            viewHolder.populate(events.get(position));
        }
    }

    public void notifyDataChanged(List<EventCommand> events) {
        this.events.clear();
        if (events != null) {
            this.events.addAll(events);
            notifyDataSetChanged();
        }
    }

    public List<EventCommand> getEvents() {
        return events;
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

        public void populate(EventCommand event) {
            switch (event.getEventType()){
                case SPORT_AND_RECREATION:
                    mainView.setCardBackgroundColor(0xC58ade9e);
                    break;
                case MUSIC:
                    mainView.setCardBackgroundColor(0xDEffdbc5);
                    break;
                case CINEMA:
                    mainView.setCardBackgroundColor(0xC5a4e9e7);
                    break;
                default:
                    break;
            }
            tvTitle.setText(event.getName());
            if (event.getDescription().length() > 70){
                tvDescription.setText(event.getDescription().substring(0, 70) + "...");
            } else {
                tvDescription.setText(event.getDescription());
            }
            tvCountFriends.setText(String.valueOf(event.getUsers().size()));
            tvComment.setText(String.valueOf(event.getCommentCommands().size()));
        }

        public View getTitle(){
            return tvTitle;
        }

        public TextView getDescription() {
            return tvDescription;
        }
    }

}
