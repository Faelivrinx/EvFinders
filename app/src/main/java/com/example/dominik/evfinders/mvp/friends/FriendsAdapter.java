package com.example.dominik.evfinders.mvp.friends;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dominik.evfinders.R;
import com.example.dominik.evfinders.database.pojo.Friend;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Dominik on 11.09.2017.
 */

public class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Friend> friendList;
    private LayoutInflater inflater;
    private boolean selectionMode = false;
    private PublishSubject<Boolean> selectionModeSubject = PublishSubject.create();



    public FriendsAdapter(List<Friend> friendList, LayoutInflater inflater, Context context) {
        this.friendList = friendList;
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder(inflater.inflate(R.layout.list_item_firends, parent, false));
            case 2:
                return new ViewHolderSelectionMode(inflater.inflate(R.layout.list_item_firends_select, parent, false));
            default:
                return new ViewHolder(inflater.inflate(R.layout.list_item_firends, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        holder.itemView.setOnLongClickListener(view -> {
            selectionMode = !selectionMode;
            selectionModeSubject.onNext(selectionMode);
            unselectItems();
            notifyDataSetChanged();
            return true;
        });

        switch (holder.getItemViewType()){
            case 0:
                ViewHolder viewHolder = (ViewHolder) holder;
                Friend friend = friendList.get(position);
                ((ViewHolder) holder).populate(friend.getImagePath(), friend.getUsername(), friend.getName());
                break;
            case 2:
                ViewHolderSelectionMode viewHolderSelection = (ViewHolderSelectionMode) holder;
                Friend friendSelection = friendList.get(position);
                viewHolderSelection.itemView.setOnClickListener(view -> {
                    friendSelection.setSelected(!friendSelection.isSelected());
                    notifyItemChanged(position);
                });
                ((ViewHolderSelectionMode) holder).populate(friendSelection.getImagePath(), friendSelection.getUsername(), friendSelection.getName(), friendSelection.isSelected());
                break;
            default:
                ViewHolder defaultHolder = (ViewHolder) holder;
                Friend friendDefault = friendList.get(position);
                ((ViewHolder) defaultHolder).populate(friendDefault.getImagePath(), friendDefault.getUsername(), friendDefault.getName());
        }

    }

    private void unselectItems() {
        for (Friend friend : friendList) {
            friend.setSelected(false);
        }
    }


    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (selectionMode) {
            return 2;
        } else {
            return 0;
        }
    }


    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public void notifyDataChange(List<Friend> friends) {
        friendList.clear();
        friendList.addAll(friends);
        notifyDataSetChanged();
    }

    public boolean isSelectionMode() {
        return selectionMode;
    }

    public Observable<Boolean> getSelectionModeSubject() {
        return selectionModeSubject;
    }


    public List<Friend> getFriendList() {
        return friendList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_friends_avatar)
        CircleImageView avatar;
        @BindView(R.id.list_item_friends_username)
        TextView username;
        @BindView(R.id.list_item_friends_name)
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void populate(String imagePath, String username, String name) {
            //avatar.setImageBitmap();
            Glide.with(context).load("http://www.gravatar.com/avatar/51?d=identicon").into(avatar);
            this.username.setText(username);
            this.name.setText(name);
        }
    }

    class ViewHolderSelectionMode extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_friends_select_avatar)
        CircleImageView avatar;
        @BindView(R.id.list_item_friends_select_username)
        TextView username;
        @BindView(R.id.list_item_friends_select_name)
        TextView name;
        @BindView(R.id.list_item_friends_select_cbIsSelected)
        CheckBox isSelected;
        @BindView(R.id.list_item_friends_select_mainLayout)
        LinearLayout mainLayout;

        public ViewHolderSelectionMode(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void populate(String imagePath, String username, String name, boolean isSelected) {
            //avatar.setImageBitmap();
            Glide.with(context).load("http://www.gravatar.com/avatar/51?d=identicon").into(avatar);
            if (isSelected) {
                mainLayout.setBackgroundColor(Color.CYAN);
            } else {
                mainLayout.setBackgroundColor(Color.TRANSPARENT);
            }
            this.isSelected.setChecked(isSelected);
            this.username.setText(username);
            this.name.setText(name);
        }

    }
}
