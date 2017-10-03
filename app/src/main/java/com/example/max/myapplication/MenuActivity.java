package com.example.max.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    EditText player1;
    EditText player2;
    List<String> playersList = new ArrayList<>();
    List<EditText> editTextList = new ArrayList<>();
    LinearLayout linearLayout;
    int position = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        player1 = (EditText) findViewById(R.id.editText1);
        player2 = (EditText) findViewById(R.id.editText2);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

    }

    public void play(View view){
        collectPlayers();
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        intent.putExtra("players", (Serializable) playersList);
        intent.putStringArrayListExtra("players", (ArrayList<String>) playersList);
        startActivity(intent);
    }

    private void collectPlayers() {
        playersList.clear();
        playersList.add(player1.getText().toString());
        playersList.add(player2.getText().toString());
        for (EditText editText: editTextList) {
            playersList.add(editText.getText().toString());
        }
    }

    public void addPlayer(View view){
        EditText editText = getEditText();
        linearLayout.addView(editText, position);
        editTextList.add(editText);
        position++;
    }

    private EditText getEditText(){
        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        editText.setText("Игрок " + position);
        return editText;
    }

}
