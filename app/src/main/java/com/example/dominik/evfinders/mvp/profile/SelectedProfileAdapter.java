package com.example.dominik.evfinders.mvp.profile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 08.11.2017.
 */

public class SelectedProfileAdapter extends RecyclerView.Adapter<SelectedProfileAdapter.Viewholder> {

    private LayoutInflater layoutInflater;
    private ProfileActivity activity;

    public SelectedProfileAdapter(LayoutInflater layoutInflater, ProfileActivity activity) {
        this.layoutInflater = layoutInflater;
        this.activity = activity;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_selected_item_profile, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.populate(activity.getSelectedItems().get(position).getName());

        holder.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            activity.getSelectedItems().get(position).setRating((int) v);
            Log.d("test", activity.getSelectedItems().get(position).getName() +  " have rating: " + activity.getSelectedItems().get(position).getRating());
        });
    }


    @Override
    public int getItemCount() {
        return activity.getSelectedItems().size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_selected_item_profile_name)
        TextView textView;

        @BindView(R.id.list_selected_item_profile_rating)
        RatingBar ratingBar;

        public Viewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(String title){
            textView.setText(title);
        }
    }
}
