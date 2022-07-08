package com.ringolatechapps.newsera;

public class Arcticle {
    String img_url;
    String heading;
    String description;
    String content;
    String url;

    public Arcticle(String img_url, String heading, String description, String content, String url) {
        this.img_url = img_url;
        this.heading = heading;
        this.description = description;
        this.content = content;
        this.url = url;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
