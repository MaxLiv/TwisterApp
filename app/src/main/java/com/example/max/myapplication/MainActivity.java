package com.example.max.myapplication;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView playerTextView;
    List<String> playersList = new ArrayList<>();
    ImageButton resetButton;

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
        resetButton = (ImageButton) findViewById(R.id.resetButton);

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

        changeViewColor(layout, resetButton,getViewBackgroundColor(layout),colors[pos]);

        setPlayer();
        changeColor(colors[pos]);
    }

    private int getViewBackgroundColor(View view) {
        int color = Color.TRANSPARENT;
        Drawable background = layout.getBackground();
        if (background instanceof ColorDrawable)
            color = ((ColorDrawable) view.getBackground()).getColor();
        return color;

    }

    private void changeViewColor(final View view1, final View view2, final int initialColor, final int finalColor) {
        // Load initial and final colors.

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();
                int blended = blendColors(initialColor, finalColor, position);

                // Apply blended color to the view.
                view1.setBackgroundColor(blended);
                view2.setBackgroundColor(blended);
            }
        });

        anim.setDuration(600).start();
    }

    private int blendColors(int from, int to, float ratio) {
        final float inverseRatio = 1f - ratio;

        final float r = Color.red(to) * ratio + Color.red(from) * inverseRatio;
        final float g = Color.green(to) * ratio + Color.green(from) * inverseRatio;
        final float b = Color.blue(to) * ratio + Color.blue(from) * inverseRatio;

        return Color.rgb((int) r, (int) g, (int) b);
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

    private void changeViewTextColor(final TextView view1, final TextView view2, final int initialColor, final int finalColor) {
        // Load initial and final colors.

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Use animation position to blend colors.
                float position = animation.getAnimatedFraction();
                int blended = blendColors(initialColor, finalColor, position);

                // Apply blended color to the view.
                view1.setTextColor(blended);
                view2.setTextColor(blended);
            }
        });

        anim.setDuration(600).start();
    }

    void changeColor(int color){
        switch (color){
            case Color.BLUE: {
//                setTheme(R.style.AppThemeBlue);
                setWhiteColorView();
                chooseBodyPart("синее");
            }
                break;
            case Color.GREEN: {
//                getTheme().applyStyle(R.style.AppThemeGreen, true);
                setBlackColorView();
                chooseBodyPart("зелёное");
            }
                break;
            case Color.RED: {
//                this.setTheme(R.style.AppThemeRed);
                setWhiteColorView();
                chooseBodyPart("красное");
            }
                break;
            case Color.YELLOW: {
//                setTheme(R.style.AppThemeYellow);
                setBlackColorView();
                chooseBodyPart("желтое");
            }
                break;
        }
    }

    private void setWhiteColorView(){
        changeViewTextColor(textView, playerTextView, textView.getCurrentTextColor(), Color.WHITE);
//        textView.setTextColor(Color.WHITE);
//        playerTextView.setTextColor(Color.WHITE);
    }

    private void setBlackColorView(){
        changeViewTextColor(textView, playerTextView, textView.getCurrentTextColor(), Color.BLACK);
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


    public void reset(View view) {
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
