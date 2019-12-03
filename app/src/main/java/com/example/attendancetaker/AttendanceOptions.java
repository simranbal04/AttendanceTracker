package com.example.attendancetaker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AttendanceOptions extends AppCompatActivity {

    Button barcodebutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendanceOptions);

        barcodebutton = (Button) findViewById(R.id.barcodebutton);
        Intent intent = new Intent(getApplicationContext(), AttendanceOptions.class);
        startActivity(intent);

    }



}
