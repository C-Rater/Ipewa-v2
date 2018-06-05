package com.craterstudio.juanfrancrater.ipewa.ui.login.View;

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

import com.craterstudio.juanfrancrater.ipewa.ui.forget.ForgetActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.Register.View.RegisterActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.view.WelcomeActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.login.Presenter.LoginPresenter;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;

/**
 * Muestra un login para iniciar sesión
 * Permite entrar con un usuario generico "user" con la contraseña "user" o manteniendo pulsado el boton de inicio de sesion
 * Permite entrar al registro
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */

public class LoginActivity extends AppCompatActivity implements LoginContrat.View {
    private static final int RC_SIGN_IN = 9001;
    private LoginContrat.Presenter presenter;
    private Button btn_SignIn,btnGoogle;
    private EditText edtUser;
    private EditText edtpassword;
    private TextView txtVIfSignUp,txtVPasswordForget;
    private CheckBox chkB_Remember;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = ThisApplication.getFirebase().getCurrentUser();
        AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
        if(currentUser!=null&&sharedPreferences.getRememberMe())
        {
            sharedPreferences.setCurrentUserName(currentUser.getDisplayName());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter= new LoginPresenter(this,this);
        btnGoogle=findViewById(R.id.btn_GoogleLogin);

        chkB_Remember=findViewById(R.id.chkB_Remember);
        final AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
        chkB_Remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPreferences.setRememberMe(isChecked);
            }
        });

        btn_SignIn = (Button) findViewById(R.id.btn_SignIn);
        btn_SignIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this, WelcomeActivity.class),0);
                return true;
            }
        });
        edtUser=findViewById(R.id.edT_User);
        edtpassword=findViewById(R.id.edT_Passw);
        txtVIfSignUp=findViewById(R.id.txtVIfSignUp);
        txtVPasswordForget=findViewById(R.id.txtVForgetPassword);
        txtVIfSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),0);
            }
        });
        txtVPasswordForget.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(LoginActivity.this,ForgetActivity.class),0);
            }
        });

        btn_SignIn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                presenter.signIn(edtUser.getText().toString(),edtpassword.getText().toString());
            }

        });



        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = ThisApplication.getmGoogleSignInClient().getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        if(sharedPreferences.getRememberMe())
        {
            Intent intnt = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intnt);
        }else{
            ThisApplication.getmGoogleSignInClient().signOut();
        }

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
            sharedPreferences.setCurrentUserName(account.getDisplayName());
            sharedPreferences.setCurrentUserID(account.getId());
            sharedPreferences.setCurrentUserID(account.getIdToken());
            sharedPreferences.setRememberMe(true);
            Intent intnt = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intnt);
            } catch (ApiException e) {
            showError("Error con Google:"+e.getMessage());
            }
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
