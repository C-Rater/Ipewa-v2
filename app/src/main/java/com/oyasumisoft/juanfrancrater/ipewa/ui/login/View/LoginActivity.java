package com.oyasumisoft.juanfrancrater.ipewa.ui.login.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.Register.View.RegisterActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.Welcome.WelcomeActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Presenter.LoginPresenter;
import com.oyasumisoft.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.oyasumisoft.juanfrancrater.ipewa.util.ThisApplication;

/**
 * Muestra un login para iniciar sesión
 * Permite entrar con un usuario generico "user" con la contraseña "user" o manteniendo pulsado el boton de inicio de sesion
 * Permite entrar al registro
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */

public class LoginActivity extends AppCompatActivity implements LoginContrat.View {

    private LoginContrat.Presenter presenter;
    private Button btn_SignIn;
    private EditText edtUser;
    private EditText edtpassword;
    private TextView txtVIfSignUp;
    private CheckBox chkB_Remember;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = ThisApplication.getFirebase().getCurrentUser();
        if(currentUser!=null)
        {
            AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter= new LoginPresenter(this,this);
        chkB_Remember=findViewById(R.id.chkB_Remember);
        final AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
        chkB_Remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.setRememberMe(isChecked);
            }
        });
        if(sharedPreferences.getRememberMe())
        {
            Intent intnt = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intnt);
        }
        btn_SignIn = (Button) findViewById(R.id.btn_SignIn);
        edtUser=findViewById(R.id.edT_User);
        edtpassword=findViewById(R.id.edT_Passw);
        txtVIfSignUp=findViewById(R.id.txtVIfSignUp);
        txtVIfSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),0);
            }
        });

        btn_SignIn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                presenter.signIn(edtUser.getText().toString(),edtpassword.getText().toString());
            }

        });



    }

    @Override
    public void Enter() {
        ThisApplication.getFirebase().signInWithEmailAndPassword(edtUser.getText().toString(), edtpassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();

                    sharedPreferences.setCurrentUserName(edtUser.getText().toString());
                    Intent intnt = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(intnt);
                } else {
                    showError("Error en inicio de sesion");
                }
            }
        });


    }

    @Override
    public void signUp() {
        presenter.signUp();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
    }
}
