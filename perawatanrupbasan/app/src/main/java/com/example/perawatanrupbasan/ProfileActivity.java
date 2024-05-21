package com.example.perawatanrupbasan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String user = intent.getStringExtra("USER").toString();
        String mail = intent.getStringExtra("MAIL").toString();

        TextView username = findViewById(R.id.tvUser);
        TextView email = findViewById(R.id.tvMail);

        username.setText("Username : " + user);
        email.setText("Email : " + mail);
    }
}
