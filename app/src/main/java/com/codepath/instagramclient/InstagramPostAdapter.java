package com.codepath.instagramclient;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ocpsoft.pretty.time.PrettyTime;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class InstagramPostAdapter extends ArrayAdapter<InstagramPost> {

    // To decide what to pass to the constructor, ask what do we need?
    // We need: context, data source
    public InstagramPostAdapter(Context context, List<InstagramPost> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    // What our item looks like
    // Use the template to display each post

    /**
     *
     * @param position What item in the list we want the view for
     * @param convertView Maybe a recycled view
     * @param parent The list view we inflate this from
     * @return The created item as a view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InstagramPost post = getItem(position);

        // Check if we are using a recycled view, if not we need to inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }

        // Lookup the views for populating the data
        RoundedImageView rivProfilePhoto = (RoundedImageView) convertView.findViewById(R.id.rivProfilePhoto);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvTimeElapsed = (TextView) convertView.findViewById(R.id.tvTimeElapsed);
        TextView tvLikesCount = (TextView) convertView.findViewById(R.id.tvLikesCount);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);

        // Insert the model data into each of the view items
        tvUsername.setText(post.getUsername());
        tvTimeElapsed.setText(new PrettyTime().format(new Date(post.getCreationTimeSeconds() * 1000)));
        tvLikesCount.setText(post.getLikesCount() + " likes");
        if (post.getCaption() != null) {
            tvCaption.setText(post.getCaption());
        }

        // Clear images because this could be a recycled view
        rivProfilePhoto.setImageResource(0);
        ivPhoto.setImageResource(0);

        if (post.getProfilePhotoUrl() != null) {
            Picasso.with(getContext()).load(post.getProfilePhotoUrl()).into(rivProfilePhoto);
        } else {
            // TODO(ageiduschek): Load some placeholder graphic
        }
        Picasso.with(getContext()).load(post.getImageUrl()).into(ivPhoto);


        // Return the created item as a view
        return convertView;
    }
}
