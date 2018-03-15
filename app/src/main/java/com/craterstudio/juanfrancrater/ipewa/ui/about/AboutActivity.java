package com.craterstudio.juanfrancrater.ipewa.ui.about;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.craterstudio.juanfrancrater.ipewa.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;


/**
 * Muestra usando la librería MaterialAbout, enlaces e informacion
 sobre las redes sociales y vías de contacto a las que puede
 usuario el usuario acceder sobre mi.
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class AboutActivity extends AppCompatActivity {

   // Using MaterialAbout  https://github.com/jrvansuita/MaterialAbout

    FrameLayout frmL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        frmL=(FrameLayout)findViewById(R.id.frmL);
        AboutBuilder build = AboutBuilder.with(this)
                .setName(getString(R.string.nameAbout))
                .setSubTitle(getString(R.string.subTitleAbout))
                .setBrief(getString(R.string.briefAbout))
                .setAppIcon(R.drawable.icon_v1)
                .setAppName(R.string.app_name)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setLinksColumnsCount(3)
                .addGitHubLink("JuanFranCrater")
                .addLinkedInLink("juan-francisco-benítez-lópez-557918130")
                .addEmailLink("juanfbenitezlopez@gmail.com")
                .setShowAsCard(true);
        AboutView view = build.build();
        frmL.addView(view);
    }
    }
