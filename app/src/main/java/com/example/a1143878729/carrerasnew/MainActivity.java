package com.example.a1143878729.carrerasnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Cliente.Observer{

    Button empezar;
    Button pausa;
    TextView p1;
    TextView p2;
    TextView p3;

    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empezar = findViewById(R.id.button);
        pausa = findViewById(R.id.butto2);
        p1 = findViewById(R.id.tv_p1);
        p2 = findViewById(R.id.tv_p2);
        p3 = findViewById(R.id.tv_p3);

        cliente = new Cliente(this);
        cliente.start();

        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente.enviar("EMPEZAR");

            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cliente.enviar("PAUSA");

            }
        });
    }

    @Override
    public void getRecibido(String recibido) {

        System.out.println("recibi mensaje de: " + recibido);

    }
}
