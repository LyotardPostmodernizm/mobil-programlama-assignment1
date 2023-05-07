package com.bignerdranch.android.newfirebasedeneme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.newfirebasedeneme.User;
import com.bignerdranch.android.newfirebasedeneme.databinding.UserListLayoutBinding;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<userModel> userList;


    public UserAdapter(ArrayList<userModel> userList){
        this.userList = userList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        UserListLayoutBinding userListlayoutBinding;
        public ViewHolder(@NonNull UserListLayoutBinding postViewBinding) {
            super(postViewBinding.getRoot());
            this.userListlayoutBinding=postViewBinding;
        }
    }



    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserListLayoutBinding postViewBinding = UserListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserAdapter.ViewHolder(postViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.userListlayoutBinding.Name.setText(userList.get(position).name);
        holder.userListlayoutBinding.surName.setText(userList.get(position).surname);
        holder.userListlayoutBinding.email.setText(userList.get(position).email);
        Picasso.get().load(userList.get(position).photo).into(holder.userListlayoutBinding.UserImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

