package com.craterstudio.juanfrancrater.ipewa.util.Chat;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;
import com.google.firebase.messaging.FirebaseMessaging;

public class ChatActivity extends AppCompatActivity {
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        input= (EditText)findViewById(R.id.input);
        setContentView(R.layout.layout_chat);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
            }
        });
    }
}
