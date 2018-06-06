package com.craterstudio.juanfrancrater.ipewa.ui.Meta.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.DiffPrioAdapter;
import com.craterstudio.juanfrancrater.ipewa.adapter.SpinnerProyectAdapter;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.DiffPrioRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats.MetaContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Presenter.EditMetaPresenter;
import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by usuario on 31/05/18.
 */

public class EditMetaActivity extends AppCompatActivity implements MetaContrat.editMeta.View{
    private MetaContrat.editMeta.Presenter presenter;
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

    Meta editMeta;
    private ArrayList<Integer> listProjId;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        presenter = new EditMetaPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.metas);
        editMeta = getIntent().getExtras().getParcelable("editMeta");
        color= editMeta.get_color();
        initialize();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.EditMeta(editMeta.get_ID(),tiedtName.getText().toString(),tiedtDescription.getText().toString(), color,deadLine,spnPrio.getSelectedItem().toString(),spnDiff.getSelectedItem().toString(),listProjId.get(spnProyecto.getSelectedItemPosition()),editMeta.get_creator());
            }
        });
    }

    private void initialize() {
        txtColor=findViewById(R.id.txtColor);
        iconColor=findViewById(R.id.iconColorPicker);
        iconColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(EditMetaActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
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
        iconColor.setShapeColor(editMeta.get_color());
        edtDate=findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditMetaActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        deadLine=editMeta.get_deadLine();
        edtDate.setText(deadLine);
        tiedtName = (TextInputEditText) findViewById(R.id.tiedtName);
        tiedtName.setText(editMeta.get_name());
        tiedtDescription = (TextInputEditText) findViewById(R.id.tiedtDescription);
        tiedtDescription.setText(editMeta.get_description());
        spnDiff=(Spinner) findViewById(R.id.spnDiff);
        spnPrio=(Spinner) findViewById(R.id.spnPrio);
        spnProyecto=(Spinner) findViewById(R.id.spnProyecto);

        spnPrio.setAdapter(new DiffPrioAdapter(this));
        for (int i = 0; i < DiffPrioRepository.getInstance().getLevels().size(); i++) {
            if (spnPrio.getItemAtPosition(i).toString().equals(editMeta.get_priority())) {
                spnPrio.setSelection(i);
            }
        }
        spnDiff.setAdapter(new DiffPrioAdapter(this));
        for (int i = 0; i < DiffPrioRepository.getInstance().getLevels().size(); i++) {
            if (spnDiff.getItemAtPosition(i).toString().equals(editMeta.get_difficulty())) {
                spnDiff.setSelection(i);
            }
        }
        presenter.getIdList();
        spnProyecto.setAdapter(new SpinnerProyectAdapter(this,-1));
        for (int i=0;i<listProjId.size();i++)
        {
            if(listProjId.get(i).equals(String.valueOf(editMeta.get_idProyecto())))
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
        Toast.makeText(EditMetaActivity.this,getResources().getString(R.string.ErrorEmptyName),Toast.LENGTH_SHORT);
    }

    @Override
    public void fillIdList(ArrayList<Integer> idProjects) {
        listProjId=idProjects;
    }
}
