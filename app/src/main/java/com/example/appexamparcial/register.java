package com.example.appexamparcial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class register extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextDate;
    private Calendar calendar;

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

        spinner = findViewById(R.id.spinner);
        editTextDate = findViewById(R.id.txt_date);
        calendar = Calendar.getInstance();

        editTextDate.setOnClickListener(v -> mostrarCuadroFecha() );

        String[] options = {"IronMan","Thor","Hulk","Spiderman"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_options,options);
        spinner.setAdapter(adapter);
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
                    editTextDate.setText(dayOfMonth + "/" + (month+1) + "/" +year);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

}