package com.example.wk01hw02androidrestapiloginandlandingpage;

import static org.junit.Assert.*;

import android.content.Intent;

import org.junit.Test;

public class LandingActivityTest {

    @Test
    public void verifyUserId() {
        LandingActivity activity = new LandingActivity();
        String user1 = "0";
        String user2 = "1";
        Post post = new Post("0", "test", "test");
        assertEquals(true, activity.verifyUserId(post, user1));
        assertNotEquals(true, activity.verifyUserId(post, user2));
    }

    @Test
    public void toLoginPage() {
        LandingActivity activity = new LandingActivity();
        IntentFactory factory = new IntentFactory();
        Intent intent1 = activity.toLoginPage(factory);
        Intent intent2 = factory.getIntent(activity, MainActivity.class);
        assertEquals(intent1.getAction(), intent2.getAction());
    }
}