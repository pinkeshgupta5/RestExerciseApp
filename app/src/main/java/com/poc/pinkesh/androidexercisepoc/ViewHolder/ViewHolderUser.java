package com.poc.pinkesh.androidexercisepoc.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.poc.pinkesh.androidexercisepoc.R;

public class ViewHolderUser extends RecyclerView.ViewHolder {
    public ImageView imageView_imageitem;
    public TextView textView_title;
    public TextView textView_description;
    public ImageView imageView_imagearrow;
    public ViewHolderUser(View itemView) {
        super(itemView);
        imageView_imageitem=(ImageView)itemView.findViewById(R.id.itemimage);
        textView_title=(TextView) itemView.findViewById(R.id.itemtitle);
        textView_description=(TextView)itemView.findViewById(R.id.itemdescription);
        imageView_imagearrow=(ImageView)itemView.findViewById(R.id.image_arrow);

    }
}
