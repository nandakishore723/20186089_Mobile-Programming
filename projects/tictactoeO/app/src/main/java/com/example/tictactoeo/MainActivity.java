package com.example.tictactoeo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity {

    Button button;
    private EditText player1;
    private EditText player2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        button = (Button) findViewById(R.id.buttonn);
        player1 = (EditText) findViewById(R.id.editName);
        if(player1.getText().toString().length() == 0 )
            player1.setError( "name is required!" );
        player2 = (EditText) findViewById(R.id.editName1);
        if(player2.getText().toString().length() == 0 )
            player2.setError( "name is required!" );
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Bundle b = new Bundle();
                b.putString("player1",player1.getText().toString());
                b.putString("player2", player2.getText().toString());
                Intent intent = new Intent(context, game.class);
                intent.putExtras(b);

                startActivity(intent);

            }

        });

    }

}

