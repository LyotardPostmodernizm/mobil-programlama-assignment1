package com.bignerdranch.android.newfirebasedeneme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.newfirebasedeneme.databinding.PostViewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UploadAdapter extends RecyclerView.Adapter<UploadAdapter.ViewHolder> {

    private ArrayList<Post> postList;


    public UploadAdapter(ArrayList<Post> postList){
        this.postList = postList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        PostViewBinding postViewBinding;
        public ViewHolder(@NonNull PostViewBinding postViewBinding) {
            super(postViewBinding.getRoot());
            this.postViewBinding=postViewBinding;
        }
    }



    @Override
    public UploadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostViewBinding postViewBinding = PostViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(postViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadAdapter.ViewHolder holder, int position) {
        holder.postViewBinding.recyclerviewname.setText(postList.get(position).name);
        holder.postViewBinding.recyclerviewsurname.setText(postList.get(position).surname);
        holder.postViewBinding.recyclerviewemail.setText(postList.get(position).email);
        Picasso.get().load(postList.get(position).Image).into(holder.postViewBinding.image);
        holder.postViewBinding.recyclerviewRowCommentText.setText(postList.get(position).comment);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


}
