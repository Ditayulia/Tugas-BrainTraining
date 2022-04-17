package com.example.dbraintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int lokasiJawaban,jawabanSalah,point, nomorSoal,a,b;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    TextView sum,score,result,count;
    GridLayout gridLayout;
    Button button0,button1,button2,button3,again,go;
    ConstraintLayout main;

    public void generateSoal()
    {
        Random rand = new Random();
       a = rand.nextInt(21);
        b = rand.nextInt(21);

        sum.setText(Integer.toString(a)+" + "+Integer.toString(b));
        lokasiJawaban = rand.nextInt(4);
        answer.clear();
        for (int i = 0;i<4;i++)
        {
            if(i == lokasiJawaban)
            {
                answer.add(a+b);
            } else {
                jawabanSalah = rand.nextInt(41);
                while (jawabanSalah == a+b)
                {
                    jawabanSalah = rand.nextInt(41);
                }
                answer.add(jawabanSalah);
            }

        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    public void countDown()
    {
        new CountDownTimer(31000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                count.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {

                gridLayout.setVisibility(View.INVISIBLE);
                again.setVisibility(View.VISIBLE);
                count.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                sum.setVisibility(View.INVISIBLE);
                count.setText("");
                score.setText("");
                sum.setText("");
                result.setText("Your Score:"+ Integer.toString(point) + "/"+Integer.toString(nomorSoal));

            }
        }.start();
    }

    public void jawaban(View view)
    {
            if(view.getTag().toString().equals(Integer.toString(lokasiJawaban)))
            {
                result.setText("Correct !");
                point++;
            } else {
                result.setText("Wrong !");
            }

            nomorSoal++;

        score.setText(Integer.toString(point)+" / "+Integer.toString(nomorSoal));
        generateSoal();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        score = findViewById(R.id.score);
        again = findViewById(R.id.again);
        sum = findViewById(R.id.sum);
        result = findViewById(R.id.result);
        count = findViewById(R.id.count);
        gridLayout = findViewById(R.id.gridLayout);
        go = findViewById(R.id.GO);
        main = findViewById(R.id.main);
        generateSoal();
        countDown();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setVisibility(v.VISIBLE);
                go.setVisibility(View.INVISIBLE);
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


           generateSoal();

                gridLayout.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
                score.setVisibility(View.VISIBLE);
                sum.setVisibility(View.VISIBLE);
                again.setVisibility(View.INVISIBLE);
                score.setText("0 / 0");
                result.setText("");
                point = 0;
                nomorSoal = 0;
                count.setText("30s");

                new CountDownTimer(31000,1000)
                {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        count.setText(String.valueOf(millisUntilFinished / 1000)+"s");
                    }

                    @Override
                    public void onFinish() {

                        gridLayout.setVisibility(View.INVISIBLE);
                        again.setVisibility(View.VISIBLE);
                        count.setVisibility(View.INVISIBLE);
                        score.setVisibility(View.INVISIBLE);
                        sum.setVisibility(View.INVISIBLE);
                        result.setText("Your Score:"+ Integer.toString(point) + "/"+Integer.toString(nomorSoal));

                    }
                }.start();
            }
        });
    }
}