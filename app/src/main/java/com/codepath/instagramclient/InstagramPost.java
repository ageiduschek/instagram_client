package com.codepath.instagramclient;

public class InstagramPost {
    private String username;
    private String profilePhotoUrl;
    private String caption;
    private String imageUrl;
    private String imageHeight;
    private Long creationTime;
    private String likesCount;

    public InstagramPost(String username, String profilePhotoUrl, String caption, String imageUrl,
                         String imageHeight, Long creationTime, String likesCount) {
        this.username = username;
        this.profilePhotoUrl = profilePhotoUrl;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.imageHeight = imageHeight;
        this.creationTime = creationTime;
        this.likesCount = likesCount;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public Long getCreationTimeSeconds() {
        return creationTime;
    }

    public String getLikesCount() {
        return likesCount;
    }
}
