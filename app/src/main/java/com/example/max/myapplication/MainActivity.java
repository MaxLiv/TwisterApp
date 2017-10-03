package com.example.max.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView playerTextView;
    List<String> playersList = new ArrayList<>();

    int count = 0;
    int size;

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.textView);
        playerTextView = (TextView) findViewById(R.id.playerTextView);
        layout = (ConstraintLayout) findViewById(R.id.mainView);

        collectPlayers();
    }

    private void collectPlayers() {
        Intent intent = getIntent();
        playersList = intent.getStringArrayListExtra("players");
        size = playersList.size();
    }


    public void click(View view){
        Random random = new Random();
        int colors[] = { Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
        int pos = random.nextInt(colors.length);
        layout.setBackgroundColor(colors[pos]);
        setPlayer();
        changeColor(colors[pos]);
    }

    private void setPlayer() {
        playerTextView.setText(playersList.get(count));
        if (count < size-1){
            count++;
        }
        else{
            count = 0;
        }
    }

    void changeColor(int color){
        switch (color){
            case Color.BLUE: {
//                setTheme(R.style.AppThemeBlue);
                chooseBodyPart("синее");
            }
                break;
            case Color.GREEN: {
//                getTheme().applyStyle(R.style.AppThemeGreen, true);
                chooseBodyPart("зелёное");
            }
                break;
            case Color.RED: {
//                this.setTheme(R.style.AppThemeRed);
                chooseBodyPart("красное");
            }
                break;
            case Color.YELLOW: {
//                setTheme(R.style.AppThemeYellow);
                chooseBodyPart("желтое");
            }
                break;
        }
    }

    void chooseBodyPart(String color){
        int a = 1; // Начальное значение диапазона - "от"
        int b = 4; // Конечное значение диапазона - "до"
        int random_number = a + (int) (Math.random() * b); // Генерация числа
        switch (random_number){
            case 1: textView.setText("Правую руку на " + color);
                break;
            case 2: textView.setText("Левую руку на "+ color);
                break;
            case 3: textView.setText("Правую ногу на " + color);
                break;
            case 4: textView.setText("Левую ногу на " + color);
                break;
        }
    }


}
