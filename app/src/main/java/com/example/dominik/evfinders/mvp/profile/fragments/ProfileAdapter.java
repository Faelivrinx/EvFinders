package com.example.dominik.evfinders.mvp.profile.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.ProfileItem;
import com.example.dominik.evfinders.mvp.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 07.11.2017.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ProfileActivity activity;
    List<ProfileItem> items = new ArrayList<>();

    public ProfileAdapter(LayoutInflater inflater, ProfileActivity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            if (!items.get(position).isSelected()) {
                holder.layout.setBackgroundColor(0xDEffdbc5);
            } else {
//                activity.removeItemFromSelected(profileItems.get(position));
                holder.layout.setBackgroundColor(0xFF7BDE6A);
                items.get(position).setRating(0);
            }

            Long id = items.get(position).getId();
            for (ProfileItem profileItem : activity.getProfilesItem()) {
                if (profileItem.getId() == id){
//                    activity.getProfilesItem().get(position).setSelected(!activity.getProfilesItem().get(position).isSelected());
                    profileItem.setSelected(!profileItem.isSelected());
                }
            }
            activity.updateItemsAdapter();
        });
        holder.populate(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void onUpdateItems(List<ProfileItem> profiles) {
        items.addAll(profiles);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_profile_layout)
        LinearLayout layout;

        @BindView(R.id.list_item_profile_text)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(String text) {
            tvName.setText(text);
        }

    }
}
