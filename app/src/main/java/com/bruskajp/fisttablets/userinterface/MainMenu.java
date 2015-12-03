package com.bruskajp.fisttablets.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View;

import com.bruskajp.fisttablets.Game.Game;
import com.bruskajp.fisttablets.Game.SinglePlayerGame;
import com.bruskajp.fisttablets.R;
import com.bruskajp.fisttablets.artificialintelligence.ComputerPlayer;
import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.networking.NetworkManager;

/**
 * Created by damonster on 10/10/15.
 */
public class MainMenu extends Activity{

    Button buttonStartLocalGame;
    Button buttonStartOnlineGame;

    private final static String LOG_TAG = "MainMenu";

    public MainMenu() {}

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
        setContentView(R.layout.activity_main);
        buttonStartLocalGame = (Button) findViewById(R.id.buttonStartLocalGame);
        buttonStartLocalGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent myIntent = new Intent(v.getContext(), GameBoard.class);
                startActivityForResult(myIntent, 0);


            }
        });

        buttonStartOnlineGame = (Button) findViewById(R.id.buttonStartOnlineGame);
        buttonStartOnlineGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "This is how a button works and how to log things to console");

                NetworkManager networkManager = new NetworkManager();
                networkManager.initializeNetworks();

                Intent myIntent = new Intent(v.getContext(), GameBoard.class);
                startActivityForResult(myIntent, 0);

            }
        });

    }

}
