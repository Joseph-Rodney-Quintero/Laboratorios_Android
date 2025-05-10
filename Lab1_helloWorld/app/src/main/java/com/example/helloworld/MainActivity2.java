package com.example.helloworld;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText caja;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showLog("Activity Created");

        caja = findViewById(R.id.cajaNum);
        Button miBoton = findViewById(R.id.button);

        miBoton.setOnClickListener(v -> finish());
    }

    private static final String HOME_ACTIVITY_TAG = MainActivity2.class.getSimpleName();

    private void showLog(String text) {
        Log.d(HOME_ACTIVITY_TAG, text);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        caja.setText("1");
        showLog("Activity restarted");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("Activity started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("Activity resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        caja.setText("");
        showLog("Activity paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("Activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("Activity is being destroyed");
    }
}
