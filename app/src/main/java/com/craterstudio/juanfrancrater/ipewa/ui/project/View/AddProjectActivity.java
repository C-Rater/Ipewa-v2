package com.craterstudio.juanfrancrater.ipewa.ui.project.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter.AddProjectPresenter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

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
    TextView txtColor;
    EditText edtDate;
    MaterialLetterIcon iconColor;
    int mYear;
    int mMonth;
    int mDay;
    String deadLine = "";
    ProjectContrat.addProject.Presenter presenter;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        presenter= new AddProjectPresenter(this,this);
        initialize();

    }
    private void datePicker()
    { Calendar mcurrentDate = Calendar.getInstance();
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
            deadLine = sdfDB.format(myCalendar.getTime());
            edtDate.setText(sdf.format(myCalendar.getTime()));
            mDay = selectedday;
            mMonth = selectedmonth;
            mYear = selectedyear;
        }
    }, mYear, mMonth, mDay);
    mDatePicker.show();

    }
    private void initialize()
    {
        tiedtName =  findViewById(R.id.tiedtName);
        tiedtDescription = findViewById(R.id.tiedtDescription);
        iconColor=findViewById(R.id.iconColorPicker);
        txtColor=findViewById(R.id.txtColor);
        edtDate =  findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        iconColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(AddProjectActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
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

    public void onClickaddProject(View v)
    {
        presenter.addProject(tiedtName.getText().toString(),
                tiedtDescription.getText().toString(),
                color,
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
