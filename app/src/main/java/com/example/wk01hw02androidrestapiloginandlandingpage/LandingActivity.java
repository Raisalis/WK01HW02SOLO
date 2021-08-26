package com.example.wk01hw02androidrestapiloginandlandingpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class LandingActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private Button buttonLogout;
    private RecyclerView recyclerViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        //Link content view
        textViewWelcome = findViewById(R.id.textViewWelcomeBack);
        buttonLogout = findViewById(R.id.buttonLogout);
        recyclerViewPosts = findViewById(R.id.recyclerViewPosts);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userId = intent.getStringExtra("userId");
        textViewWelcome.setText(getString(R.string.welcome_back) + " " + username);
        //TODO: add User Posts

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LandingActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
