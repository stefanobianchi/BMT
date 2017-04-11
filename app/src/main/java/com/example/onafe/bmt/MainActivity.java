package com.example.onafe.bmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.TextEmail);
        password = (EditText)findViewById(R.id.TextPassword);
        ImageButton login = (ImageButton)findViewById(R.id.imageButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("stefano.bianchi@stmitalia.it")&&
                        password.getText().toString().equals("")){
                    Intent intent = new Intent(
                            getApplicationContext(),
                            Riepilogo2Activity.class
                    );
                    startActivity(intent);
                }
            }
        });
    }
}
