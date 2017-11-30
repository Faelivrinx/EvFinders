package com.example.dominik.evfinders.mvp.home.create.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.ProfileItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dominik on 17.11.2017.
 */

public class CreateEventAdapter extends RecyclerView.Adapter<CreateEventAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;

    private List<ProfileItem> profileItems = new ArrayList<>();

    public CreateEventAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mainLayout.setOnClickListener(view -> {
            profileItems.get(position).setSelected(!profileItems.get(position).isSelected());
            if (profileItems.get(position).isSelected()){
                profileItems.get(position).setRating(6);
            } else {
                profileItems.get(position).setRating(0);
            }
            notifyDataSetChanged();
        });

        holder.populate(profileItems.get(position));
    }

    @Override
    public int getItemCount() {
        return profileItems.size();
    }

    public void setProfileItems(List<ProfileItem> items){
        if (items.size() > 0){
            profileItems.clear();
            profileItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public List<ProfileItem> getProfileItems() {
        return profileItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_item_profile_text)
        TextView tvProfileName;

        @BindView(R.id.list_item_profile_layout)
        LinearLayout mainLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(ProfileItem item){
            tvProfileName.setText(item.getName());

            if (item.isSelected()){
                mainLayout.setBackgroundColor(0xDEffdbc5);
            } else {
                mainLayout.setBackgroundColor(0xFF7BDE6A);
            }
        }
    }
}
