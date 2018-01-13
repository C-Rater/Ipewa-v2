package com.oyasumisoft.juanfrancrater.ipewa.ui.project.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.ColorAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.AddProjectPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Permite añadir un proyecto al sistema. Muestra los campos
 que se añadirán al repositorio/base de datos y un ActionButton
 para confirmar los datos que se guardaran.
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class AddProjectActivity extends AppCompatActivity implements ProjectContrat.addProject.View {
    TextInputEditText tiedtName;
    TextInputEditText tiedtDescription;
    Spinner spnColor;
    Button btnDeadline;
    TextView txtVDeadLine;
    int mYear;
    int mMonth;
    int mDay;
    String deadLine = "";
    ProjectContrat.addProject.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        presenter= new AddProjectPresenter(this,this);
        initialize();
        btnDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    mYear = mcurrentDate.get(Calendar.YEAR);
                    mMonth = mcurrentDate.get(Calendar.MONTH);
                    mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker = new DatePickerDialog(AddProjectActivity.this, new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                            Calendar myCalendar = Calendar.getInstance();
                            myCalendar.set(Calendar.YEAR, selectedyear);
                            myCalendar.set(Calendar.MONTH, selectedmonth);
                            myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                            String myFormatTextView = "dd/MM/yyyy";
                            String myFormatBD = "yyyy/MM/dd";
                            SimpleDateFormat sdf = new SimpleDateFormat(myFormatTextView);
                            SimpleDateFormat sdfDB = new SimpleDateFormat(myFormatBD);
                            deadLine=sdfDB.format(myCalendar.getTime());
                            txtVDeadLine.setText(sdf.format(myCalendar.getTime()));

                            mDay = selectedday;
                            mMonth = selectedmonth;
                            mYear = selectedyear;
                        }
                    }, mYear, mMonth, mDay);
                    mDatePicker.show();
                }
        });
    }

    private void initialize()
    {
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtName);
        tiedtDescription = (TextInputEditText) findViewById(R.id.tiedtDescription);
        spnColor = (Spinner) findViewById(R.id.spnColor);
        spnColor.setAdapter(new ColorAdapter(this));
        btnDeadline = (Button) findViewById(R.id.btnDatePicker);
        txtVDeadLine=(TextView) findViewById(R.id.txtVDeadLine);
    }

    public void onClickaddProject(View v)
    {
        presenter.addProject(tiedtName.getText().toString(),
                tiedtDescription.getText().toString(),
                spnColor.getSelectedItem().toString(),
                deadLine);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void back() {
        finish();
    }
}
