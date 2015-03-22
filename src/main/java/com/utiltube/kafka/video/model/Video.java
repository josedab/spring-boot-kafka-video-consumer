package com.utiltube.kafka.video.model;

public class Video {

    private static final int IMPOSSIBLE_ID = -1;

    private int id = IMPOSSIBLE_ID;
    private String identifier;
    private String provider;
    private String title;
    private String description;

    public int getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getProvider() {
        return provider;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
