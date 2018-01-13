package com.oyasumisoft.juanfrancrater.ipewa.ui.project.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.ColorAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ColorRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.EditProjectPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Permite editar uno de los proyecto del sistema. Muestra los
 campos que se tenia en el repositorio/base de datos y
 un ActionButton para confirmar los datos que se guardaran.
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class EditProjectActivity extends AppCompatActivity implements ProjectContrat.editProject.View {

    ProjectContrat.editProject.Presenter presenter;
    TextInputEditText tiedtName;
    TextInputEditText tiedtDescription;
    Spinner spnColor;
    Button btnDeadline;
    TextView txtVDeadLine;
    int mYear;
    int mMonth;
    int mDay;
    String deadLine = "";

    Proyecto editProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditProjectPresenter(this);
        setContentView(R.layout.activity_add_project);
        presenter.getProject( getIntent().getExtras().getInt("editProject"));
        if(editProject!=null)
        {
            initialize();
        }
    }

    private void initialize() {
        btnDeadline = (Button) findViewById(R.id.btnDatePicker);
        txtVDeadLine = (TextView) findViewById(R.id.txtVDeadLine);
        btnDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditProjectActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormatTextView = "dd/MM/yyyy";
                        String myFormatBD = "yyyy/MM/dd";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormatTextView);
                        SimpleDateFormat sdfDB = new SimpleDateFormat(myFormatBD);
                        deadLine = sdfDB.format(myCalendar.getTime());
                        txtVDeadLine.setText(sdf.format(myCalendar.getTime()));
                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });

        txtVDeadLine.setText(editProject.get_deadLine().substring(8,10)+editProject.get_deadLine().substring(4,8)+editProject.get_deadLine().substring(0,4));
        deadLine=editProject.get_deadLine();
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtName);
        tiedtName.setText(editProject.get_name());
        tiedtDescription = (TextInputEditText) findViewById(R.id.tiedtDescription);
        tiedtDescription.setText(editProject.get_description());
        spnColor = (Spinner) findViewById(R.id.spnColor);
        spnColor.setAdapter(new ColorAdapter(this));
        for (int i = 0; i < ColorRepository.getInstance().getColors().size(); i++) {
            if (spnColor.getItemAtPosition(i).toString().equals(editProject.get_color())) {
                spnColor.setSelection(i);
            }
        }
    }

    public void onClickaddProject(View v)
    {
        presenter.EditProject(editProject.get_ID(),tiedtName.getText().toString(),tiedtDescription.getText().toString(),spnColor.getSelectedItem().toString(),deadLine);
    }

    @Override
    public void back() {
        Toast.makeText(this, R.string.createdProject, Toast.LENGTH_SHORT).show();
     finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(EditProjectActivity.this,error,Toast.LENGTH_SHORT);
    }

    @Override
    public void loadProject(Proyecto project) {
        editProject=project;
    }
}

