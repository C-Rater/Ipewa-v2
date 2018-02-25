package com.oyasumisoft.juanfrancrater.ipewa.ui.task.View;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.ColorAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.DiffPrioAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.SpinnerProyectAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.AddProjectPresenter;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.View.AddProjectActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.AddTaskPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Permite Añadir una tarea
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class AddTaskActivity extends AppCompatActivity implements TaskContrat.addTask.View {

    TextInputEditText tiedtName;
    TextInputEditText tiedtDescription;
    Spinner spnColor;
    Spinner spnPrio;
    Spinner spnDiff;
    Spinner spnProyecto;
    Button btnDeadline;
    TextView txtVDeadLine;
    int mYear;
    int mMonth;
    int mDay;
    String deadLine = "";
    ArrayList<String> listProjId;
    TaskContrat.addTask.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addTask(tiedtName.getText().toString(),
                        tiedtDescription.getText().toString(),
                        spnColor.getSelectedItem().toString(),
                        deadLine,spnPrio.getSelectedItem().toString(),
                        spnDiff.getSelectedItem().toString(), Integer.parseInt(listProjId.get(spnProyecto.getSelectedItemPosition())),1);
            }
        });

        presenter= new AddTaskPresenter(this,this);
        initialize();
        btnDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        spnDiff=(Spinner) findViewById(R.id.spnDiff);
        spnPrio=(Spinner) findViewById(R.id.spnPrio);
        spnProyecto=(Spinner) findViewById(R.id.spnProyecto);
        spnProyecto.setAdapter(new SpinnerProyectAdapter(this));
        presenter.getIdList();
        spnColor.setAdapter(new ColorAdapter(this));
        spnDiff.setAdapter(new DiffPrioAdapter(this));
        spnPrio.setAdapter(new DiffPrioAdapter(this));
        btnDeadline = (Button) findViewById(R.id.btnDatePicker);
        txtVDeadLine=(TextView) findViewById(R.id.txtVDeadLine);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(AddTaskActivity.this,error,Toast.LENGTH_SHORT);
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void fillIdList(ArrayList<String> idProjects) {
        listProjId=idProjects;
    }
}
