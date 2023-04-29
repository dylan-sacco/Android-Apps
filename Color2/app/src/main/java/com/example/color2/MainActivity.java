package com.example.color2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_color_inputs);
    }
    public void SetColor(View view) {
        TextInputEditText redT = findViewById(R.id.RC), blueT=findViewById(R.id.BC), greenT =  findViewById(R.id.GC);
        String red= String.valueOf(redT.getText()).toUpperCase(), blue= String.valueOf(blueT.getText()).toUpperCase(),green = String.valueOf(greenT.getText()).toUpperCase();
        try {
            findViewById(R.id.button).setBackgroundColor(Color.parseColor("#" + red + blue + green));
        }catch (Exception e){
            Toast.makeText(view.getContext(),"That is an illegal Input",Toast.LENGTH_SHORT).show();
        }

    }
}