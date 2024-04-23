package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int wt=0,ht=0,htSub=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView result_btn;
        Button bmi_btn;
        ImageView bmiPin;
        EditText edtWeight, edtHeight, edtHeightIn;
//        LinearLayout llmain;
        AppCompatSpinner weightSpinner, heightSpinner;
//        Animation anim_move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bmi_rotate);
        bmiPin=findViewById(R.id.bmiPin);
        edtWeight= findViewById(R.id.edtWeight);
        edtHeightIn= findViewById(R.id.edtHeightIn);
        edtHeight =findViewById(R.id.edtHeight);
        result_btn=findViewById(R.id.result_btn);
        bmi_btn =findViewById(R.id.bmi_btn);
//        llmain = findViewById(R.id.llmain);
        weightSpinner=findViewById(R.id.wtSpinner);
        heightSpinner=findViewById(R.id.htSpinner);

      /*  RotateDrawable rotateDrawable = (RotateDrawable) bmiPin.getDrawable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rotateDrawable.setPivotX(50);
            rotateDrawable.setPivotY(79.25F);
            rotateDrawable.setFromDegrees(93);
        }*/

        ArrayList<String> heightArr=new ArrayList<>();
        heightArr.add("Feet");
        heightArr.add("Centimeter");
        heightArr.add("Meter");
        ArrayAdapter<String> heightAdaptor=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,heightArr);
        heightSpinner.setAdapter(heightAdaptor);
        heightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    edtHeight.setHint("Ft");
                    edtHeightIn.setHint("Inch");
                    edtHeight.setEms(5);
                    ht=0;
                    htSub=0;
                    edtHeightIn.setVisibility(View.VISIBLE);
                    edtHeightIn.setEms(5);
                    edtHeightIn.setText("");
                    edtHeight.setText("");

                } else if (i == 1) {
                    edtHeight.setEms(10);
                    edtHeightIn.setVisibility(View.GONE);
                    edtHeight.setHint("Centimeter");
                    ht=1;
                    edtHeightIn.setText("");
                    edtHeight.setText("");
                }else if (i==2){
                    edtHeight.setHint("Meter");
                    edtHeightIn.setHint("cm");
                    edtHeight.setEms(5);
                    ht=2;
                    htSub=2;
                    edtHeightIn.setVisibility(View.VISIBLE);
                    edtHeightIn.setEms(5);
                    edtHeightIn.setText("");
                    edtHeight.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> weightArr=new ArrayList<>();
        weightArr.add("Kilogram");
        weightArr.add("Pounds");
        ArrayAdapter<String> weightArrayAdaptor=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,weightArr);
        weightSpinner.setAdapter(weightArrayAdaptor);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    wt=0;
                    edtWeight.setHint("Weight(Kg)");
                    edtWeight.setText("");
                } else if (i==1) {
                    wt=1;
                    edtWeight.setHint("Weight(Pound)");
                    edtWeight.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bmi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double wtResult= 0.0;
                double htResult= 0.0;
                double htSubResult=0.0;
                double totalHeight=0.0;

                if (!edtWeight.getText().toString().isEmpty()) {


                    if (!edtHeight.getText().toString().isEmpty()) {
                        if (wt == 0) {

                            wtResult = Double.parseDouble((edtWeight.getText().toString()));
                            // Integer.parseInt(edtWeight.getText().toString());
                        } else if (wt == 1) {
                            wtResult = Double.parseDouble(edtWeight.getText().toString()) * 2.20462;
                        }


                        if (ht == 0) {
                            htResult = (Double.parseDouble(edtHeight.getText().toString()) * 0.3048);
                        } else if (ht == 1) {
                            htResult = (Double.parseDouble(edtHeight.getText().toString()) / 100);
                        } else if (ht == 2) {
                            htResult = Double.parseDouble(edtHeight.getText().toString());
                        }


                        if (!edtHeightIn.getText().toString().isEmpty()) {
                            if (ht == 0) {
                                htSubResult = (Double.parseDouble(edtHeightIn.getText().toString()) / 12);
                                htSubResult = htSubResult * 0.3048;
                            } else if (ht == 1) {
                                htSubResult = Double.parseDouble(edtHeightIn.getText().toString()) / 100;
                            } else if (ht == 2) {
                                htSubResult = Double.parseDouble(edtHeightIn.getText().toString()) / 100;
                            }
                            totalHeight = htResult + htSubResult;
                            double bmiResult = wtResult / (totalHeight * totalHeight);
                            double finalResult = Math.round(bmiResult * 10.0) / 10.0;
                            result_btn.setText("Your BMI is " + finalResult);
                            Log.d("FinamlResultBmi", "onClick: " + finalResult);

                            if (0<finalResult&&40>finalResult){
                                int resultDegree= (int) ((finalResult*4.5)-4);
                                Animation anim1=new RotateAnimation(4,-resultDegree,bmiPin.getWidth()*0.2088F,bmiPin.getHeight()/2F);
                                anim1.setDuration(2000);
                                anim1.setFillAfter(true);
                                bmiPin.startAnimation(anim1);
                            } else if (40==finalResult||40<finalResult) {

                                Animation anim1=new RotateAnimation(0,-188,bmiPin.getWidth()*0.2088F,bmiPin.getHeight()/2F);
                                anim1.setDuration(2000);
                                anim1.setFillAfter(true);
                                bmiPin.startAnimation(anim1);
                            }


                        }else {

                            totalHeight = htResult + htSubResult;
                            double bmiResult = wtResult / (totalHeight * totalHeight);
                            double finalResult = Math.round(bmiResult * 10.0) / 10.0;
                            result_btn.setText("Your BMI is " + finalResult);
                            Log.d("FinamlResultBmi", "onClick: " + finalResult);

                            if (0<finalResult&&40>finalResult){
                                int resultDegree= (int) ((finalResult*4.5)-4);
                                Animation anim1=new RotateAnimation(4,-resultDegree,bmiPin.getWidth()*0.2088F,bmiPin.getHeight()/2F);
                                anim1.setDuration(2000);
                                anim1.setFillAfter(true);
                                bmiPin.startAnimation(anim1);
                            } else if (40==finalResult||40<finalResult) {

                                Animation anim1=new RotateAnimation(0,-188,bmiPin.getWidth()*0.2088F,bmiPin.getHeight()/2F);
                                anim1.setDuration(2000);
                                anim1.setFillAfter(true);
                                bmiPin.startAnimation(anim1);
                            }


                        }

                    }else  {
                        result_btn.setText("Please Enter Height");
                    }
                }

                else {
                    result_btn.setText("Please Enter Weight and Height");
                }
            }
        });

    }

}