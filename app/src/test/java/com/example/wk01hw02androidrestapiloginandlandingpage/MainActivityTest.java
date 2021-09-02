package com.example.wk01hw02androidrestapiloginandlandingpage;

import static org.junit.Assert.*;

import android.content.Intent;

import org.junit.Test;

public class MainActivityTest {

    @Test
    public void verifyUsername() {
        MainActivity activity = new MainActivity();
        String user1 = "steve";
        String user2 = "steve";
        assertEquals(true, activity.verifyUsername(user1, user2));
        assertNotEquals(false, activity.verifyUsername(user1, user2));
    }

    @Test
    public void verifyPassword() {
        MainActivity activity = new MainActivity();
        String pass1 = "password";
        String pass2 = "password";
        assertEquals(true, activity.verifyPassword(pass1, pass2));
        assertNotEquals(false, activity.verifyPassword(pass1, pass2));
    }

    @Test
    public void toLandingPage() {
        MainActivity activity = new MainActivity();
        IntentFactory factory = new IntentFactory();
        User user = new User();
        user.setUsername("steve");
        user.setUserId(1);
        Intent intent1 = activity.toLandingPage(factory, user);
        Intent intent2 = factory.getIntent(activity, LandingActivity.class);
        intent2.putExtra("username", user.getUsername());
        intent2.putExtra("userId", user.getUserId());
        assertEquals(intent1.getAction(), intent2.getAction());
    }
}