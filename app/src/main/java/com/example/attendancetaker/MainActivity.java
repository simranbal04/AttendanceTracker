package com.example.attendancetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton,cancelButton;
    EditText edusername,edpassword;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.button);
        cancelButton = (Button)findViewById(R.id.button2);

        edusername = (EditText)findViewById(R.id.edusername);
        edpassword = (EditText)findViewById(R.id.edpassword);

        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (edusername.getText().toString().equals("admin") &&  edpassword.getText().toString().equals("admin"))
                {
                    Toast.makeText(getApplicationContext(),"Redirecting",Toast.LENGTH_SHORT).show();


//                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                    startActivity(intent);
                }

                else {
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show();
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0 )
                    {
                        loginButton.setEnabled(false);
                    }

                }
            }
        }) ;

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

