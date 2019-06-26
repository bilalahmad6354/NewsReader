package com.example.newsreader.models;

public class Post {

    public String description;
    public String link;
    public String publish_date;
    public String title;
    public String img;

    public Post(){
        this.description = "";
        this.link = "";
        this.publish_date = "";
        this.title = "";
        this.img = "";
    }

    public Post(String description, String link, String publish_date, String title, String img) {
        this.description = description;
        this.link = link;
        this.publish_date = publish_date;
        this.title = title;
        this.img = img;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
