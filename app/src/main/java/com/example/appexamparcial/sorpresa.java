package com.example.appexamparcial;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Arrays;
import java.util.Random;

public class sorpresa extends AppCompatActivity {

    private String usuario;
    TextView txt_victoria;
    TextView txt_saludo;
    Integer[] botones;
    int[] tablero = new int[]{
            0,0,0,
            0,0,0,
            0,0,0
    };

    int estado = 0;
    int fichasPuestas = 0;
    int turno = 1;
    private int[] fichasX = new int[3];
    private int[] fichasO = new int[3];
    int[] posGanadora = new int[]{-1,-1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sorpresa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usuario = getIntent().getStringExtra("txt_usuario");
        if (usuario != null){
            txt_saludo = findViewById(R.id.txt_saludo);
            txt_saludo.setText("Hola "+usuario);
        }

        txt_victoria = findViewById(R.id.textVictoria);
        txt_victoria.setVisibility(View.INVISIBLE);
        botones = new Integer[]{
                R.id.b1,R.id.b2,R.id.b3,
                R.id.b4,R.id.b5,R.id.b6,
                R.id.b7,R.id.b8,R.id.b9,
        };
    }

    public boolean ponerFicha(View v){
        if(estado == 0){
            //turno =  1;
            int i=0;
            int j=0;
            int numBoton = Arrays.asList(botones).indexOf(v.getId());

            if (tablero[numBoton] == 0 && turno==1){
                Button button = (Button) v;
                button.setText("X");
                tablero[numBoton] = 1;
                fichasPuestas += 1;

                /*if(i<=2){
                    fichasX[i]=numBoton;
                    i++;
                }else{

                }*/
                estado = comprobarEstado();
                terminarPartida();
                turno= -1;
                return true;
            }
            if (tablero[numBoton] == 0 && turno==-1) {
                Button button = (Button) v;
                button.setText("O");
                tablero[numBoton] = -1;
                fichasPuestas += 1;
                estado = comprobarEstado();
                terminarPartida();
                turno = 1;
                return true;
            }
        }
        return true;
    }

    public void terminarPartida(){
        if (estado == 1 || estado == -1){
            if (estado == 1){
                txt_victoria.setVisibility(View.VISIBLE);
                txt_victoria.setTextColor(Color.GREEN);
                txt_victoria.setText("Ha ganado el jugador 1 :)");
            }else {
                txt_victoria.setVisibility(View.VISIBLE);
                txt_victoria.setTextColor(Color.BLUE);
                txt_victoria.setText("Ha ganado el jugador 2 :)");
            }
            for (int i = 0; i < posGanadora.length; i++) {
                Button b = findViewById(botones[posGanadora[i]]);
                b.setTextColor(Color.GREEN);
            }
        } /*else if (estado == 2) {
            txt_victoria.setVisibility(View.VISIBLE);
            txt_victoria.setText("Has empatado :|");
        }*/
    }

    /*public void ia(){
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while (tablero[pos] != 0){
            pos = ran.nextInt(tablero.length);
        }

        Button b = (Button) findViewById(botones[pos]);
        b.setText("O");
        tablero[pos] = -1;
    }*/

    public int comprobarEstado(){
        int nuevoEstado = 0;
        if (Math.abs(tablero[0] + tablero[1] + tablero[2])  == 3){
            posGanadora = new int[]{0,1,2};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[3] + tablero[4] + tablero[5])  == 3){
            posGanadora = new int[]{3,4,5};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[6] + tablero[7] + tablero[8])  == 3){
            posGanadora = new int[]{6,7,8};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[0] + tablero[3] + tablero[6])  == 3){
            posGanadora = new int[]{0,3,6};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[1] + tablero[4] + tablero[7])  == 3){
            posGanadora = new int[]{1,4,7};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[2] + tablero[5] + tablero[8])  == 3){
            posGanadora = new int[]{2,5,8};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[0] + tablero[4] + tablero[8])  == 3){
            posGanadora = new int[]{0,4,8};
            nuevoEstado = 1*turno;
        }else if (Math.abs(tablero[2] + tablero[4] + tablero[6])  == 3){
            posGanadora = new int[]{2,4,6};
            nuevoEstado = 1*turno;
        }else if(fichasPuestas == 9){
            nuevoEstado = 2;
        }

        return nuevoEstado;
    }

    public void verificar(){

    }

    public void resetGame(View v) {
        Arrays.fill(tablero, 0);
        fichasPuestas = 0;
        estado = 0;
        turno = 1;
        posGanadora = new int[]{-1, -1, -1};

        for (int id : botones) {
            Button button = findViewById(id);
            button.setText("");
            button.setTextColor(getResources().getColor(com.google.android.material.R.color.m3_ref_palette_white,null));
        }

        txt_victoria.setVisibility(View.INVISIBLE);
    }
}