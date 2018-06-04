package com.craterstudio.juanfrancrater.ipewa.ui.forget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Created by usuario on 31/05/18.
 */

public class ForgetActivity extends AppCompatActivity {

    private EditText inputEmail;

    private Button btnReset;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset_password);

        inputEmail = (EditText) findViewById(R.id.email);

        btnReset = (Button) findViewById(R.id.btn_reset_password);

        auth =  FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), R.string.enterEmail, Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetActivity.this, R.string.resetComplet, Toast.LENGTH_SHORT).show();
                                    FirebaseMessagingService sender = new FirebaseMessagingService();

                                    finish();
                                } else {
                                    Toast.makeText(ForgetActivity.this, R.string.failedReset, Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

}
