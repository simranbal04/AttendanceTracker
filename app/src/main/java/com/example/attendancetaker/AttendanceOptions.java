package com.example.attendancetaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AttendanceOptions extends AppCompatActivity {

    Button barcodebutton;
    Button facebutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendanceoptions);

        barcodebutton = (Button) findViewById(R.id.barcodebutton);

        barcodebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AttendanceBarcode.class);
                startActivity(intent);
            }
        });


        facebutton = (Button) findViewById(R.id.facebutton);



    }



}
