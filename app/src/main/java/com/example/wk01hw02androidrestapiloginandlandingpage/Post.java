package com.example.wk01hw02androidrestapiloginandlandingpage;

import java.util.Objects;

public class Post {
    private String userId;
    private String title;
    private String body;

    public Post(String userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        this.userId = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post1 = (Post) o;
        return Objects.equals(userId, post1.userId) && Objects.equals(title, post1.title) && Objects.equals(body, post1.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, body);
    }

    @Override
    public String toString() {
        return "Title: " + title + '\n' + body;
    }
}
