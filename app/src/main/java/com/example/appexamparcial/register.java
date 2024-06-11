package com.example.appexamparcial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class register extends AppCompatActivity {

    private Spinner spn_option;
    private EditText txt_date;
    private Calendar calendar;

    private EditText txt_name;
    private EditText txt_apellido;
    private RadioGroup sexo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spn_option = findViewById(R.id.spinner);
        txt_date = findViewById(R.id.txt_date);
        calendar = Calendar.getInstance();

        txt_name = findViewById(R.id.txt_name_register);
        txt_apellido = findViewById(R.id.txt_surname_register);
        sexo = findViewById(R.id.radioGroup);

        txt_date.setOnClickListener(v -> mostrarCuadroFecha() );

        String[] options = {"IronMan","Thor","Hulk","Spiderman"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_options,options);
        spn_option.setAdapter(adapter);
    }

    public boolean Registrar(View v){
        String name = txt_name.getText().toString();
        String surname = txt_apellido.getText().toString();
        String option = spn_option.getSelectedItem().toString();
        int selectRadioGroupId = sexo.getCheckedRadioButtonId();
        RadioButton selectRadioButton = findViewById(selectRadioGroupId);
        String sexo = selectRadioButton.getText().toString();
        String date = txt_date.getText().toString();

        if(name.length() > 10){
            Toast.makeText(this,"El nombre de usuario es muy grande", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!name.equals("") && !surname.equals("") && !option.equals("") && !sexo.equals("") && !date.equals("")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("txt_usuario",name);
            intent.putExtra("txt_contraseña",surname);
            intent.putExtra("txt_option",option);
            intent.putExtra("txt_sexo",sexo);
            intent.putExtra("txt_date",date);
            startActivity(intent);
            finish();
            return true;
        }else {
            Toast.makeText(this,"Falta rellenar datos en el formulario", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void Regresar(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void mostrarCuadroFecha(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
          this,
                (view, year, month, dayOfMonth) -> {
                    txt_date.setText(dayOfMonth + "/" + (month+1) + "/" +year);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

}