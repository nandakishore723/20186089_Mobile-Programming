package com.example.tictactoeo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class game extends AppCompatActivity  implements View.OnClickListener{
    Button button;
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;
    String str= "";
    String str1 = "";

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

  private   TextView player1;

 private TextView player2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
           textViewPlayer1 = findViewById(R.id.text_view_p1);
            textViewPlayer2 = findViewById(R.id.text_view_p2);
            Bundle b = getIntent().getExtras();
         player1 = (TextView) findViewById(R.id.text_view_p1);
         player2 = (TextView) findViewById(R.id.text_view_p2);
         str = (player1.getText().toString());
         str1 = str1 + player2.getText().toString();

        player1.setText(b.getCharSequence("player1") + ": 0" );
        player2.setText(b.getCharSequence("player2")+ ": 0");





            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    String buttonID = "button_" + i + j;
                    int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    buttons[i][j] = findViewById(resID);
                    buttons[i][j].setOnClickListener(this);
                }
            }

        Toast.makeText(this, "first "+b.getCharSequence("player1")+"'s turn", Toast.LENGTH_LONG).show();
            Button buttonReset = findViewById(R.id.button_reset);
            buttonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = getIntent().getExtras();
                    textViewPlayer1.setText(b.getCharSequence("player1")+": 0");
                    textViewPlayer2.setText(b.getCharSequence("player2")+": 0");
                }
            });
        }

    @Override
    public void onClick(View v) {

        Bundle b = getIntent().getExtras();

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {

            ((Button) v).setText("X");
            //Toast.makeText(this, "now "+b.getCharSequence("player2")+"'s turn", Toast.LENGTH_SHORT).show();
        } else {

            ((Button) v).setText("O");
            //Toast.makeText(this, "now "+b.getCharSequence("player1")+"'s turn", Toast.LENGTH_SHORT).show();
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }


    }
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        Bundle b = getIntent().getExtras();
        player1Points++;
        Toast.makeText(this, b.getCharSequence("player1")+"  won the match", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        Bundle b = getIntent().getExtras();
        player2Points++;
        Toast.makeText(this, b.getCharSequence("player2")+"  won the match", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {

        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        Bundle b = getIntent().getExtras();

        player1.setText(b.getCharSequence("player1") ) ;

        player2.setText(b.getCharSequence("player2") );


        textViewPlayer1.setText(b.getCharSequence("player1")+":" + player1Points);
        textViewPlayer2.setText(b.getCharSequence("player2") +":" + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }
}



