package com.craterstudio.juanfrancrater.ipewa.ui.task.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.DiffPrioAdapter;
import com.craterstudio.juanfrancrater.ipewa.adapter.SpinnerProyectAdapter;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.DiffPrioRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.project.View.AddProjectActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter.EditTaskPresenter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

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
    Spinner spnPrio;
    Spinner spnDiff;
    Spinner spnProyecto;
    TextView txtColor;
    EditText edtDate;
    MaterialLetterIcon iconColor;
    int mYear;
    int mMonth;
    int mDay;
    String deadLine = "";

    Tarea editTask;
    private ArrayList<Integer> listProjId;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        presenter = new EditTaskPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tareas);
        setSupportActionBar(toolbar);
        editTask = (Tarea) getIntent().getExtras().getParcelable("editTask");
        color= editTask.get_color();
        initialize();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.EditTask(editTask.get_ID(),tiedtName.getText().toString(),tiedtDescription.getText().toString(), color,deadLine,spnPrio.getSelectedItem().toString(),spnDiff.getSelectedItem().toString(),listProjId.get(spnProyecto.getSelectedItemPosition()),editTask.get_idTablero(),editTask.get_creator());
            }
        });
    }

    private void initialize() {
        txtColor=findViewById(R.id.txtColor);
        iconColor=findViewById(R.id.iconColorPicker);
        iconColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(EditTaskActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setTitle("ColorPicker Dialog");
                builder.setPositiveButton(getString(R.string.btnOK), new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        txtColor.setText("#" + colorEnvelope.getColorHtml());
                        color= colorEnvelope.getColor();
                        iconColor.setShapeColor(colorEnvelope.getColor());
                    }
                });

                builder.setCancelable(true);
                builder.show();
            }
        });
        iconColor.setShapeColor(editTask.get_color());
        edtDate=findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
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
                        edtDate.setText(sdf.format(myCalendar.getTime()));
                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });

        deadLine=editTask.get_deadLine();
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtName);
        tiedtName.setText(editTask.get_name());
        tiedtDescription = (TextInputEditText) findViewById(R.id.tiedtDescription);
        tiedtDescription.setText(editTask.get_description());
        spnDiff=(Spinner) findViewById(R.id.spnDiff);
        spnPrio=(Spinner) findViewById(R.id.spnPrio);
        spnProyecto=(Spinner) findViewById(R.id.spnProyecto);

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
    public void fillIdList(ArrayList<Integer> idProjects) {
        listProjId=idProjects;
    }
}
