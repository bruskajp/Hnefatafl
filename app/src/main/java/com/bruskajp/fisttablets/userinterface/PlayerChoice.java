package com.bruskajp.fisttablets.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.bruskajp.fisttablets.R;
import com.bruskajp.fisttablets.player.Player;

/**
 * Created by damonster on 12/7/15.
 */
public class PlayerChoice extends Activity{

    Button buttonWhitePlayer;
    Button buttonBlackPlayer;

    private final static String LOG_TAG = "MainMenu";

    public PlayerChoice() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //String value = intent.getStringExtra("this worked");
        this.initializeMenu();
    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeMenu(){
        setContentView(R.layout.single_player_choice);

        buttonWhitePlayer = (Button) findViewById(R.id.buttonWhitePlayer);
        buttonWhitePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(), GameBoard.class);
                myIntent.putExtra("color", "white");
                myIntent.putExtra("players", 1);
                startActivityForResult(myIntent, 0);

            }
        });

        buttonBlackPlayer = (Button) findViewById(R.id.buttonBlackPlayer);
        buttonBlackPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i(LOG_TAG, "This is how a button works and how to log things to console");

                Intent myIntent = new Intent(v.getContext(), GameBoard.class);
                myIntent.putExtra("color", "black");
                myIntent.putExtra("players", 1);
                startActivityForResult(myIntent, 0);

            }
        });

    }

}
