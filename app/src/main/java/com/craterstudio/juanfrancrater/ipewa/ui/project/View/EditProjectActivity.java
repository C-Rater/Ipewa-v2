package com.craterstudio.juanfrancrater.ipewa.ui.project.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.ColorAdapter;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ColorRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter.EditProjectPresenter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

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
    TextView txtColor;
    EditText edtDate;
    MaterialLetterIcon iconColor;
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
        edtDate =  findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        deadLine=editProject.get_deadLine();
        edtDate.setText(deadLine);
        tiedtName = findViewById(R.id.tiedtName);
        tiedtName.setText(editProject.get_name());
        tiedtDescription =  findViewById(R.id.tiedtDescription);
        tiedtDescription.setText(editProject.get_description());
        iconColor=findViewById(R.id.iconColorPicker);
        txtColor=findViewById(R.id.txtColor);
        iconColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(EditProjectActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
                builder.setTitle("ColorPicker Dialog");
                builder.setPositiveButton(getString(R.string.btnOK), new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        txtColor.setText("#" + colorEnvelope.getColorHtml());
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
        presenter.EditProject(editProject.get_ID(),tiedtName.getText().toString(),tiedtDescription.getText().toString(),txtColor.getText().toString(),deadLine);
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
    private void datePicker()
    {
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
                edtDate.setText(sdf.format(myCalendar.getTime()));
                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

    }
}

