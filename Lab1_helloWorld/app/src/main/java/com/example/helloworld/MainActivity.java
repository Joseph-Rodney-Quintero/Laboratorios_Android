package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText txtMensaje;
    private CheckBox check;

    private String miFecha = "";

    @SuppressLint("MissingInflatedId")
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

        Button btnImprimir = findViewById(R.id.btnHola);
        txtMensaje = findViewById(R.id.textView);
        check = findViewById(R.id.checkBox);
        CalendarView miCalendario = findViewById(R.id.calendarView);
        ImageButton imgButton = findViewById(R.id.imageButton);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm");
        String hora = formatoFecha.format(new Date());

        btnImprimir.setOnClickListener(v -> {
            String msgMensaje = txtMensaje.getText().toString();
            msgMensaje += ", " + check.isChecked();
            msgMensaje += ", " + hora;
            msgMensaje += ", " + miFecha;

            Toast.makeText(getApplicationContext(), msgMensaje, Toast.LENGTH_LONG).show();

            Snackbar snack = Snackbar.make(v, "Hola Mundo, estoy en clase de Android", Snackbar.LENGTH_LONG);
            snack.show();
        });

        miCalendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> miFecha = dayOfMonth + "/" + (month + 1) + "/" + year);

        imgButton.setOnClickListener(v -> {
            Intent puente = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(puente);
        });
    }
}
