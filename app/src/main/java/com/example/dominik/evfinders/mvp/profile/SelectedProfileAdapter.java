package com.example.dominik.evfinders.mvp.profile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.ProfileItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 08.11.2017.
 */

public class SelectedProfileAdapter extends RecyclerView.Adapter<SelectedProfileAdapter.Viewholder> {

    private LayoutInflater layoutInflater;
    private ProfileActivity activity;

    List<ProfileItem> selectedItems = new ArrayList<>();

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
        holder.populate(selectedItems.get(position).getName(), selectedItems.get(position).getRating());

        holder.ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            selectedItems.get(position).setRating((int)v);
        });
    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }


    public void updateItems() {
        selectedItems.clear();
        for (ProfileItem profileItem : activity.getProfilesItem()) {
            if (profileItem.isSelected()){
                selectedItems.add(profileItem);
            }
        }

        notifyDataSetChanged();
    }

    public List<ProfileItem> getSelectedItems() {
        return selectedItems;
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

        public void populate(String title, int rating){
            ratingBar.setRating(rating);
            textView.setText(title);
        }
    }
}
