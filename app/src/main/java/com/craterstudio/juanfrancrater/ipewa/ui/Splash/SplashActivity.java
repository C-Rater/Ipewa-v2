package com.craterstudio.juanfrancrater.ipewa.ui.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.login.View.LoginActivity;


/**
 * Muestra el icono de la aplicación. En esta versión se accede
 Al login pulsando en la imagen.
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class SplashActivity extends AppCompatActivity {
    ImageView imgV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgV= (ImageView)findViewById(R.id.imageView);
        Toast.makeText(this, R.string.pressImg, Toast.LENGTH_SHORT).show();

        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view==imgV)
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
}
