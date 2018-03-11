package com.example.feihualing3;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    int num;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.textView2);
                showResult(input.getText().toString());

            }

        });
    }


    public void showResult(String str) {

        TextView textView = (TextView) findViewById(R.id.textView2);

        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        InputStream context = getClass().getClassLoader().getResourceAsStream("assets/shi.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(context));
        String answer = "", line, title = " ";

        try {

            while ((line = br.readLine()) != null) {

                if (line.contains("：")) {
                    title = line.substring(3);
                }

                if (line.contains(str) && line.charAt(0) > '9') {
                    answer += line + "\t" + title + "\n";
                    num++;

                }


            }

            SpannableStringBuilder builder = new SpannableStringBuilder(answer);
            for (int i = 0; i <= answer.length() - str.length(); i++) {
                if (answer.substring(i, i + str.length()).equals(str)) {
                    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
                    builder.setSpan(redSpan, i, i + str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);}}

            textView.setText("共发现" + num + "条结果\n" + answer);
            num=0;



        } catch (IOException e) {}



    }}
