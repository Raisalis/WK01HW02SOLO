package com.example.wk01hw02androidrestapiloginandlandingpage;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void getUsername() {
        User testUser = new User();
        testUser.setUsername("Bob");
        assertEquals("Bob", testUser.getUsername());
    }

    @Test
    public void setUsername() {
        User testUser = new User();
        testUser.setUsername("Bob");
        assertEquals("Bob", testUser.getUsername());
    }

    @Test
    public void getPassword() {
        User testUser = new User();
        testUser.setPassword("password");
        assertEquals("password", testUser.getPassword());
    }

    @Test
    public void setPassword() {
        User testUser = new User();
        testUser.setPassword("password");
        assertEquals("password", testUser.getPassword());
    }

    @Test
    public void getUserId() {
        User testUser = new User();
        testUser.setUserId(0);
        assertEquals(0, testUser.getUserId());
    }

    @Test
    public void setUserId() {
        User testUser = new User();
        testUser.setUserId(0);
        assertEquals(0, testUser.getUserId());
    }

    @Test
    public void testEquals() {
        User testUser1 = new User();
        User testUser2 = new User();
        User testUser3 = new User();
        testUser1.setUserId(0);
        testUser1.setUsername("Bob");
        testUser1.setPassword("password");
        testUser2.setUserId(0);
        testUser2.setUsername("Bob");
        testUser2.setPassword("password");
        testUser3.setUserId(1);
        testUser3.setUsername("Anna");
        testUser3.setPassword("pass");
        assertEquals(testUser1, testUser2);
        assertNotEquals(testUser1, testUser3);
    }
}