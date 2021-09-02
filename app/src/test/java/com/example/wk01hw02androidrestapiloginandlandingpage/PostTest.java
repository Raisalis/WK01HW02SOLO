package com.example.wk01hw02androidrestapiloginandlandingpage;

import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {

    @Test
    public void getUserid() {
        Post post = new Post("0", "testTitle", "testBody");
        assertEquals("0", post.getUserid());
    }

    @Test
    public void setUserid() {
        Post post = new Post("0", "testTitle", "testBody");
        post.setUserid("1");
        assertNotEquals("0", post.getUserid());
        assertEquals("1", post.getUserid());
    }

    @Test
    public void getTitle() {
        Post post = new Post("0", "testTitle", "testBody");
        assertEquals("testTitle", post.getTitle());
    }

    @Test
    public void setTitle() {
        Post post = new Post("0", "testTitle", "testBody");
        post.setTitle("testTitle2");
        assertNotEquals("testTitle", post.getTitle());
        assertEquals("testTitle2", post.getTitle());
    }

    @Test
    public void getBody() {
        Post post = new Post("0", "testTitle", "testBody");
        assertEquals("testBody", post.getBody());
    }

    @Test
    public void setBody() {
        Post post = new Post("0", "testTitle", "testBody");
        post.setBody("testBody2");
        assertNotEquals("testBody", post.getBody());
        assertEquals("testBody2", post.getBody());
    }

    @Test
    public void testEquals() {
        Post post1 = new Post("0", "testTitle", "testBody");
        Post post2 = new Post("0", "testTitle", "testBody");
        assertEquals(post1, post2);
        post2.setBody("testBody2");
        assertNotEquals(post1, post2);
    }

    @Test
    public void testToString() {
        Post post1 = new Post("0", "testTitle", "testBody");
        String text = "Title: testTitle\ntestBody";
        assertEquals(text, post1.toString());
    }
}