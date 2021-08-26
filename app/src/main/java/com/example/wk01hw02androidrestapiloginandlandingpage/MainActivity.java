package com.example.wk01hw02androidrestapiloginandlandingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewWelcome;
    private Button buttonLogin;
    private TextView textViewError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking view content
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewError = findViewById(R.id.textViewError);

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //Intent Factory object
        IntentFactory factory = new IntentFactory();

        //When login button is pressed
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data of users from api
                Call<List<User>> call = jsonPlaceHolderApi.getUsers();

                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        if(!response.isSuccessful()) {
                            textViewError.setText("Code: " + response.code() + "\nPlease try again.");
                            return;
                        }

                        //Only take the info of the first three users (usernames: Bret, Antonette, Samantha)
                        List<User> users = response.body().subList(0, 3);

                        //Use the same password for all three (password: password)
                        for(User user : users) {
                            user.setPassword("password");
                        }

                        for(User user : users) {
                            String dataUsername = "";
                            dataUsername = user.getUsername();
                            String username = editTextUsername.getText().toString();
                            if(verifyUsername(dataUsername, username)) {
                                //Check the password against user data
                                String dataPassword = "";
                                dataPassword = user.getPassword();
                                String password = editTextPassword.getText().toString();
                                if(verifyPassword(dataPassword, password)) {
                                    //If passwords match, go to landing page (send username and userid):
                                    Intent intent = toLandingPage(factory, user);
                                    startActivity(intent);
                                    return;
                                }
                            }
                        }

                        textViewError.setText("Username or password not found.\nPlease try again.");
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        textViewError.setText(t.getMessage() + "\nPlease try again.");
                    }
                });
            }
        });
    }

    public Boolean verifyUsername(String dataUsername, String username) {
        return dataUsername.equals(username);
    }

    public Boolean verifyPassword(String dataPassword, String password) {
        return dataPassword.equals(password);
    }

    public Intent toLandingPage(IntentFactory factory, User user) {
        //If passwords match, go to landing page (send username):
        Intent intent = factory.getIntent(MainActivity.this, LandingActivity.class);
        intent.putExtra("username", user.getUsername());
        intent.putExtra("userId", user.getUserId());
        return intent;
    }
}