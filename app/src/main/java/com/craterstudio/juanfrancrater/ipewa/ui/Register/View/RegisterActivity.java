package com.craterstudio.juanfrancrater.ipewa.ui.Register.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.Register.Contrats.SigninContract;
import com.craterstudio.juanfrancrater.ipewa.ui.Register.Presenter.RegisterPresenter;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;

/**
 * Permite Añadir un nuevo usuario comprobando que los datos sean validos.
 * Luego podra iniciar sesion con el mismo
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class RegisterActivity extends AppCompatActivity implements SigninContract.View{

    EditText edtPassword,edtPasswordAgain,edtEmail;
    Button btnSignIn;
    SigninContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        edtPasswordAgain=findViewById(R.id.edtPasswordAgain);
        btnSignIn=findViewById(R.id.btnSignIn);
        presenter= new RegisterPresenter(this);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateCredentials(edtPassword.getText().toString(),edtPasswordAgain.getText().toString(),edtEmail.getText().toString());
            }
        });
    }

    @Override
    public void onSuccess() {
        ThisApplication.getFirebase().createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    finish();

                }else{
                    Toast.makeText(RegisterActivity.this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onUserEmptyError() {
        Toast.makeText(this,getResources().getString(R.string.userEmptyError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordEmptyError() {
        Toast.makeText(this,getResources().getString(R.string.passwordEmptyError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailEmptyError() {
        Toast.makeText(this,getResources().getString(R.string.emailEmptyError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordError() {
        Toast.makeText(this,getResources().getString(R.string.passwordError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailError() {
        Toast.makeText(this,getResources().getString(R.string.emailError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserDuplicated() {
        Toast.makeText(this,getResources().getString(R.string.userAlreadyExits),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailDuplicated() {
        Toast.makeText(this,getResources().getString(R.string.emailAlreadyExits),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserError() {
        Toast.makeText(this,getResources().getString(R.string.userError),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordDifferent() {
        Toast.makeText(this,getResources().getString(R.string.passwordDifferent),Toast.LENGTH_SHORT).show();
    }
}
