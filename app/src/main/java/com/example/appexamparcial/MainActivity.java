package com.example.appexamparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText cuenta;
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cuenta = findViewById(R.id.txt_cuenta);
        contrasena = findViewById(R.id.txt_password);
    }

    public void Register(View view) {

        Intent i = new Intent(this, register.class);
        startActivity(i);
        finish();
    }

    public void Ingresar(View view){
        String nameCuenta = String.valueOf(cuenta.getText().toString());
        String password = String.valueOf(contrasena.getText().toString());

        if (nameCuenta.equals("MarvinJara22" ) && password.equals("123456")){
            Intent i = new Intent(this, sorpresa.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Lo siento, la cuenta o contraseña es incorrecta",Toast.LENGTH_LONG).show();
        }
    }
}