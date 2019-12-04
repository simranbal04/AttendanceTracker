package com.example.attendancetaker;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity
{
//    Button studentlistbutton;
    Button attendanceButton;
    Spinner ShowClass;
    EditText editTextName;
    Button ViewButton;


    //database connection
    DatabaseReference databaseStudents;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        textView.setText(currentDateandTime);

        databaseStudents = FirebaseDatabase.getInstance().getReference("student");
        ViewButton = (Button) findViewById(R.id.studentlistbutton);
        ShowClass = (Spinner) findViewById(R.id.spinner1);
        editTextName = (EditText) findViewById(R.id.editText);

//        studentlistbutton = (Button)findViewById(R.id.studentlistbutton);
        attendanceButton = (Button) findViewById(R.id.attendanceButton);

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
            Toast.makeText(this, "Student Added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "you should enter a class", Toast.LENGTH_LONG).show();
        }
    }

//        Bundle b = getIntent().getExtras();
//        TextView nameValue = (TextView) findViewById(R.id.name1);
//        nameValue.setText(b.getCharSequence("name"));

        //get the spinner from the xml.
//        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
//        String[] items = new String[]{"MAD-5254", "MAD-5314"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);
//
//        studentlistbutton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Toast.makeText(getApplicationContext(),"TESTING",Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(getApplicationContext(), AttendanceOptions.class);
//                startActivity(intent);
            };
//        });
//
//        attendanceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"TEST",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), AttendanceOptions.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//}

