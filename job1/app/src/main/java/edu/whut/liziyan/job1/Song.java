package edu.whut.liziyan.job1;

/**
 * Created by Lzy on 17/10/9.
 */

public class Song {

    private String name;
    private String author;
    private Integer image;

    public Song(String name, String author, Integer image) {
        this.name = name;
        this.author = author;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
