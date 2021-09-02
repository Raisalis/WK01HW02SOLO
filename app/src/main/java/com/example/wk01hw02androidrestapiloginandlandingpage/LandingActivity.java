package com.example.wk01hw02androidrestapiloginandlandingpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private Button buttonLogout;
    private ScrollView scrollView;
    private TextView textViewScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        //Link content view
        textViewWelcome = findViewById(R.id.textViewWelcomeBack);
        buttonLogout = findViewById(R.id.buttonLogout);
        scrollView = findViewById(R.id.scrollView);
        textViewScroll = findViewById(R.id.textViewScroll);

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userId = intent.getStringExtra("userId");
        textViewWelcome.setText(getString(R.string.welcome_back) + " " + username);

        //Get data of user posts from api
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()) {
                    textViewScroll.setText("Code: " + response.code() + "\nPlease try again.");
                    return;
                }

                //Only take the info of the user id provided
                List<Post> allPosts = response.body();
                List<Post> userPosts = new ArrayList<>();
                for(Post post : allPosts) {
                    if(verifyUserId(post, userId)) {
                        userPosts.add(post);
                    }
                }

                String text = "";
                Integer count = 1;
                for(Post post: userPosts) {
                    text += "Post " + count + "\n--------------\n" + post.toString() + "\n\n";
                    count++;
                }

                textViewScroll.setText(text);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewScroll.setText(t.getMessage() + "\nPlease try again.");
            }
        });


        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentFactory factory = new IntentFactory();
                Intent intent2 = toLoginPage(factory);
                startActivity(intent2);
            }
        });
    }

    public Boolean verifyUserId(Post post, String userId) {
        if(post.getUserid().equals(userId)) {
            return true;
        } else {
            return false;
        }
    }
    public Intent toLoginPage(@NonNull IntentFactory factory) {
        Intent intent = factory.getIntent(LandingActivity.this, MainActivity.class);
        return intent;
    }
}
