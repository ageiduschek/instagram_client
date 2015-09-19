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

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InstagramPostAdapter extends ArrayAdapter<InstagramPost> {

    // To decide what to pass to the constructor, ask what do we need?
    // We need: context, data source
    public InstagramPostAdapter(Context context, List<InstagramPost> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private static class PostSubViews {
        TextView username;
        RoundedImageView profilePhoto;
        TextView timeElapsed;
        TextView likesCount;
        TextView caption;
        ImageView photo;
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

        PostSubViews subViews;
        // Check if we are using a recycled view, if not we need to inflate
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
            subViews = new PostSubViews();
            subViews.profilePhoto = (RoundedImageView) convertView.findViewById(R.id.rivProfilePhoto);
            subViews.username = (TextView) convertView.findViewById(R.id.tvUsername);
            subViews.timeElapsed = (TextView) convertView.findViewById(R.id.tvTimeElapsed);
            subViews.likesCount = (TextView) convertView.findViewById(R.id.tvLikesCount);
            subViews.caption = (TextView) convertView.findViewById(R.id.tvCaption);
            subViews.photo = (ImageView) convertView.findViewById(R.id.ivPhoto);
            convertView.setTag(subViews);
        } else {
            subViews = (PostSubViews) convertView.getTag();
        }

        // Lookup the views for populating the data


        // Insert the model data into each of the view items
        subViews.username.setText(post.getUsername());
        subViews.timeElapsed.setText(new PrettyTime().format(new Date(post.getCreationTimeSeconds() * 1000)));
        String formattedLikes = NumberFormat.getNumberInstance(Locale.getDefault()).format(post.getLikesCount());
        subViews.likesCount.setText(formattedLikes + " " + getContext().getResources().getQuantityString(R.plurals.likes, post.getLikesCount()));
        if (post.getCaption() != null) {
            subViews.caption.setText(post.getCaption());
        }

        // Clear images because this could be a recycled view
        subViews.profilePhoto.setImageResource(0);
        subViews.photo.setImageResource(0);

        if (post.getProfilePhotoUrl() != null) {
            Picasso.with(getContext()).load(post.getProfilePhotoUrl()).into(subViews.profilePhoto);
        } else {
            // TODO(ageiduschek): Load some placeholder graphic
        }
        Picasso.with(getContext()).load(post.getImageUrl()).into(subViews.photo);


        // Return the created item as a view
        return convertView;
    }
}
