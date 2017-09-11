package com.example.dominik.evfinders.mvp.friends;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private List<Friend> friendList;

    private LayoutInflater inflater;


    public FriendsAdapter(List<Friend> friendList, LayoutInflater inflater) {
        this.friendList = friendList;
        this.inflater = inflater;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_firends, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.populate(friend.getImagePath(), friend.getUsername(), friend.getName());
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public void notifyDataChange(List<Friend> friends){
        friendList.clear();
        friendList.addAll(friends);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_item_friends_avatar)        CircleImageView avatar;
        @BindView(R.id.list_item_friends_username)      TextView username;
        @BindView(R.id.list_item_friends_name)          TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void populate(String imagePath, String username, String name){
            //avatar.setImageBitmap();
            this.username.setText(username);
            this.name.setText(name);
        }
    }
}
