package com.oyasumisoft.juanfrancrater.ipewa.ui.task.View;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ColorRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.DiffPrioRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.EditTaskPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Permite editar una tarea
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class EditTaskActivity extends AppCompatActivity implements TaskContrat.editTask.View{
   private TaskContrat.editTask.Presenter presenter;
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

    Tarea editTask;
    private ArrayList<String> listProjId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        presenter = new EditTaskPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTask = (Tarea) getIntent().getExtras().getParcelable("editTask");
        initialize();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.EditTask(editTask.get_ID(),tiedtName.getText().toString(),tiedtDescription.getText().toString(),spnColor.getSelectedItem().toString(),deadLine,spnPrio.getSelectedItem().toString(),spnDiff.getSelectedItem().toString(),Integer.parseInt(listProjId.get(spnProyecto.getSelectedItemPosition())),1);
            }
        });
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

                DatePickerDialog mDatePicker = new DatePickerDialog(EditTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        txtVDeadLine.setText(editTask.get_deadLine().substring(8,10)+editTask.get_deadLine().substring(4,8)+editTask.get_deadLine().substring(0,4));
        deadLine=editTask.get_deadLine();
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtName);
        tiedtName.setText(editTask.get_name());
        tiedtDescription = (TextInputEditText) findViewById(R.id.tiedtDescription);
        tiedtDescription.setText(editTask.get_description());
        spnColor = (Spinner) findViewById(R.id.spnColor);
        spnDiff=(Spinner) findViewById(R.id.spnDiff);
        spnPrio=(Spinner) findViewById(R.id.spnPrio);
        spnProyecto=(Spinner) findViewById(R.id.spnProyecto);

        spnColor.setAdapter(new ColorAdapter(this));
        for (int i = 0; i < ColorRepository.getInstance().getColors().size(); i++) {
            if (spnColor.getItemAtPosition(i).toString().equals(editTask.get_color())) {
                spnColor.setSelection(i);
            }
        }

        spnPrio.setAdapter(new DiffPrioAdapter(this));
        for (int i = 0; i < DiffPrioRepository.getInstance().getLevels().size(); i++) {
            if (spnPrio.getItemAtPosition(i).toString().equals(editTask.get_priority())) {
                spnPrio.setSelection(i);
            }
        }
        spnDiff.setAdapter(new DiffPrioAdapter(this));
        for (int i = 0; i < DiffPrioRepository.getInstance().getLevels().size(); i++) {
            if (spnDiff.getItemAtPosition(i).toString().equals(editTask.get_difficulty())) {
                spnDiff.setSelection(i);
            }
        }
        presenter.getIdList();
        spnProyecto.setAdapter(new SpinnerProyectAdapter(this));
        for (int i=0;i<listProjId.size();i++)
        {
            if(listProjId.get(i).equals(String.valueOf(editTask.get_idProyecto())))
            {
                spnProyecto.setSelection(i);
            }
        }

    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void showError() {
        Toast.makeText(EditTaskActivity.this,getResources().getString(R.string.ErrorEmptyName),Toast.LENGTH_SHORT);
    }

    @Override
    public void fillIdList(ArrayList<String> idProjects) {
        listProjId=idProjects;
    }
}
