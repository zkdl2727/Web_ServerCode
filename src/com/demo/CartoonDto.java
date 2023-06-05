package com.demo;

public class CartoonDto {

    private int idx;
    private String title;
    private String image;
    private String content;


    public CartoonDto(int idx, String title, String image, String content) {
        this.idx = idx;
        this.title = title;
        this.image = image;
        this.content = content;
    }


    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "CartoonDto{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
