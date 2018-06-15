package com.poc.pinkesh.androidexercisepoc.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.poc.pinkesh.androidexercisepoc.Usermodels.Rows;
import com.poc.pinkesh.androidexercisepoc.R;
import com.poc.pinkesh.androidexercisepoc.ViewHolder.ViewHolderUser;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<ViewHolderUser>{

    Context ctx;
    ArrayList<Rows> userlist= new ArrayList<>();

    public UserAdapter(Context applicationContext, ArrayList<Rows> userlist) {
        this.ctx = applicationContext;
        this.userlist = userlist;
    }



    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_layout, parent, false);
        ViewHolderUser userview = new ViewHolderUser(item);

        return userview;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        Glide.with(ctx)
                .load(userlist.get(position).getImageHref()).into(holder.imageView_imageitem);
        holder.textView_description.setText(userlist.get(position).getDescription());
        holder.textView_title.setText(userlist.get(position).getTitle());
        if(userlist.get(position).getTitle()!=null  || userlist.get(position).getDescription()!=null) {
            holder.imageView_imagearrow.setVisibility(View.VISIBLE);
            }else{
            holder.imageView_imagearrow.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
}
