package com.example.attendancetaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.WriterException;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class Screen2 extends AppCompatActivity {

    Spinner ShowClass;
    EditText editTextName; // edittext
    Button ViewButton;
    ImageView qrcode; // qr d image lae
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Button createbutton;

    String inputvalue;

    String TAG="GenearatorQRCode";

    //database connection
    DatabaseReference databaseStudents;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scree2);
        TextView textView = findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        textView.setText(currentDateandTime);



        databaseStudents = FirebaseDatabase.getInstance().getReference("student");
        ViewButton = (Button) findViewById(R.id.studentlistbutton);
        ShowClass = (Spinner) findViewById(R.id.spinner1);
        editTextName = (EditText) findViewById(R.id.editText);

        // barcode
        qrcode = (ImageView) findViewById(R.id.qrcode);
        createbutton = (Button) findViewById(R.id.createbutton);


        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputvalue = editTextName.getText().toString().trim();
                if (inputvalue.length()>0) {
                    WindowManager manager = (WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smallerdimension=width<height ? width:height;
                    smallerdimension=smallerdimension*3/4;
                    qrgEncoder = new QRGEncoder(inputvalue,null, QRGContents.Type.TEXT,smallerdimension);
                    try {
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrcode.setImageBitmap(bitmap);

                    }
                    catch (WriterException e)
                    {
                        Log.v(TAG,e.toString());
                    }
                }
                else
                {
                    editTextName.setError("Required");
                }
            }
        });

        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });


    }

    private void addName() {
        String text = editTextName.getText().toString().trim();
        String className = ShowClass.getSelectedItem().toString();

        if (!TextUtils.isEmpty(text)) {

            String  id = databaseStudents.push().getKey();

            Students student = new Students(id,text,className);
            databaseStudents.child(id).setValue(student);
            databaseStudents.child(text).setValue(student);
            Toast.makeText(this, "Student Added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "you should enter a class", Toast.LENGTH_LONG).show();
        }

//        attendanceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"TEST",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), AttendanceOptions.class);
//                startActivity(intent);
//            }
//        });


    }
}
