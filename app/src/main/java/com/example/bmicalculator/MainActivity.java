package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView result_btn;
        Button bmi_btn;
        EditText edtWeight, edtHeight, edtHeightIn;
        LinearLayout llmain;

        edtWeight= findViewById(R.id.edtWeight);
        edtHeightIn= findViewById(R.id.edtHeightIn);
        edtHeight =findViewById(R.id.edtHeight);
        result_btn=findViewById(R.id.result_btn);
        bmi_btn =findViewById(R.id.bmi_btn);
        llmain = findViewById(R.id.llmain);


        bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              int wt=  Integer.parseInt(edtWeight.getText().toString());
              int ft= Integer.parseInt(edtHeight.getText().toString());
              int in= Integer.parseInt(edtHeightIn.getText().toString());
              int total_inch=(12*ft)+in;
                double height_meter= (double) (total_inch * 0.0254);
                double bmi=wt/(height_meter*height_meter);
                if (bmi>25){
                    result_btn.setText("YOU ARE OVERWEIGHT !");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorOW ));

                } else if (bmi<18) {
                    result_btn.setText("You are under Weight !");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorUW));

                }else {
                    result_btn.setText("You are Healthy !");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorH));
                }
            }
        });


    }
}