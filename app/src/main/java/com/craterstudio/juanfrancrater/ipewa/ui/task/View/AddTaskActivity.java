package com.craterstudio.juanfrancrater.ipewa.ui.task.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.DiffPrioAdapter;
import com.craterstudio.juanfrancrater.ipewa.adapter.SpinnerProyectAdapter;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter.AddTaskPresenter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

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
    ArrayList<Integer> listProjId;
    TaskContrat.addTask.Presenter presenter;
    private int color;

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
                        color,
                        deadLine,spnPrio.getSelectedItem().toString(),
                        spnDiff.getSelectedItem().toString(), listProjId.get(spnProyecto.getSelectedItemPosition()),1);
            }
        });

        presenter= new AddTaskPresenter(this,this);
        initialize();
        edtDate.setOnClickListener(new View.OnClickListener() {
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
                        edtDate.setText(sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.show();
            }
        });
        int id;
        try {
             id = getIntent().getExtras().getInt("idProyecto");
            if (id != 0) {
                for (int i = 0; i < listProjId.size(); i++) {
                    if (id == listProjId.get(i)) {
                        spnProyecto.setSelection(i);
                    }
                }
            }
        }catch (NullPointerException e)
        {
        }
    }

    private void initialize() {
        tiedtName = findViewById(R.id.tiedtName);
        tiedtDescription =findViewById(R.id.tiedtDescription);
        iconColor=findViewById(R.id.iconColorPicker);
        txtColor=findViewById(R.id.txtColor);
        spnDiff = findViewById(R.id.spnDiff);
        spnPrio =  findViewById(R.id.spnPrio);
        spnProyecto =  findViewById(R.id.spnProyecto);
        spnProyecto.setAdapter(new SpinnerProyectAdapter(this));
        presenter.getIdList();
        spnDiff.setAdapter(new DiffPrioAdapter(this));
        spnPrio.setAdapter(new DiffPrioAdapter(this));
        edtDate =  findViewById(R.id.edtDate);
        iconColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(AddTaskActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setTitle("ColorPicker Dialog");
                builder.setPositiveButton(getString(R.string.btnOK), new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        txtColor.setText("#" + colorEnvelope.getColorHtml());
                        color=colorEnvelope.getColor();
                        iconColor.setShapeColor(colorEnvelope.getColor());
                    }
                });
                builder.setCancelable(true);
                builder.show();
            }
        });
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
    public void fillIdList(ArrayList<Integer> idProjects) {
        listProjId=idProjects;
    }
}
