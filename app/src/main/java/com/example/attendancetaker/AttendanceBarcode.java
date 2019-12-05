package com.example.attendancetaker;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttendanceBarcode extends AppCompatActivity {
    String TAG="GenearatorQRCode";
    EditText edittext;
    ImageView qrcode;
    Button createbutton;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    String inputvalue;

    DatabaseReference databaseStudents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendancebarcode);
        edittext = (EditText) findViewById(R.id.edittext);
        qrcode = (ImageView) findViewById(R.id.qrcode);
        createbutton = (Button) findViewById(R.id.createbutton);
//        bitmap = (Bitmap) findViewById(R.id.bit)


        databaseStudents = FirebaseDatabase.getInstance().getReference("student");

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputvalue = edittext.getText().toString().trim();
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
                    edittext.setError("Required");
                }
            }
        });


    }
}
