package com.bruskajp.fisttablets.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.*;
import android.widget.Button;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import java.util.Random;

import com.bruskajp.fisttablets.R;

/**
 * Created by josephd on 11/16/15.
 */
public class GameBoard extends Activity{

    Button buttonExit;
    ImageView board;
    ImageView kingPiece;
    ImageView black1;
    ImageView black2;
    ImageView black3;
    ImageView black4;
    ImageView black5;
    ImageView black6;
    ImageView black7;
    ImageView black8;
    ImageView black9;
    ImageView black10;
    ImageView black11;
    ImageView black12;
    ImageView black13;
    ImageView black14;
    ImageView black15;
    ImageView black16;
    ImageView black17;
    ImageView black18;
    ImageView black19;
    ImageView black20;
    ImageView black21;
    ImageView black22;
    ImageView black23;
    ImageView black24;
    ImageView white1;
    ImageView white2;
    ImageView white3;
    ImageView white4;
    ImageView white5;
    ImageView white6;
    ImageView white7;
    ImageView white8;
    ImageView white9;
    ImageView white10;
    ImageView white11;
    ImageView white12;


    private final static String LOG_TAG = "GameBoard";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "initiated");
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String value = intent.getStringExtra("this worked");
        setContentView(R.layout.boardgame);
        board = (ImageView) findViewById(R.id.board);

        //int pieceSize = getWindowManager().getDefaultDisplay().getWidth() / 11;

        kingPiece = (ImageView) findViewById(R.id.kingpiece);

       // kingPiece.getLayoutParams().height = pieceSize;
       //ncd  kingPiece.getLayoutParams().width = pieceSize;



        black1 = (ImageView) findViewById(R.id.black1);
        black2 = (ImageView) findViewById(R.id.black2);
        black3 = (ImageView) findViewById(R.id.black3);
        black4 = (ImageView) findViewById(R.id.black4);
        black5 = (ImageView) findViewById(R.id.black5);
        black6 = (ImageView) findViewById(R.id.black6);
        black7 = (ImageView) findViewById(R.id.black7);
        black8 = (ImageView) findViewById(R.id.black8);
        black9 = (ImageView) findViewById(R.id.black9);
        black10 = (ImageView) findViewById(R.id.black10);
        black11 = (ImageView) findViewById(R.id.black11);
        black12 = (ImageView) findViewById(R.id.black12);
        black13 = (ImageView) findViewById(R.id.black13);
        black14 = (ImageView) findViewById(R.id.black14);
        black15 = (ImageView) findViewById(R.id.black15);
        black16 = (ImageView) findViewById(R.id.black16);
        black17 = (ImageView) findViewById(R.id.black17);
        black18 = (ImageView) findViewById(R.id.black18);
        black19 = (ImageView) findViewById(R.id.black19);
        black20 = (ImageView) findViewById(R.id.black20);
        black21 = (ImageView) findViewById(R.id.black21);
        black22 = (ImageView) findViewById(R.id.black22);
        black23 = (ImageView) findViewById(R.id.black23);
        black24 = (ImageView) findViewById(R.id.black24);

        white1 = (ImageView) findViewById(R.id.white1);
        white2 = (ImageView) findViewById(R.id.white2);
        white3 = (ImageView) findViewById(R.id.white3);
        white4 = (ImageView) findViewById(R.id.white4);
        white5 = (ImageView) findViewById(R.id.white5);
        white6 = (ImageView) findViewById(R.id.white6);
        white7 = (ImageView) findViewById(R.id.white7);
        white8 = (ImageView) findViewById(R.id.white8);
        white9 = (ImageView) findViewById(R.id.white9);
        white10 = (ImageView) findViewById(R.id.white10);
        white11 = (ImageView) findViewById(R.id.white11);
        white12 = (ImageView) findViewById(R.id.white12);

        buttonExit = (Button) findViewById(R.id.exit);


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


    public void initializeMenu() {
        final Random rand = new Random();
        kingPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "king pressed");
                kingPiece.animate().x(rand.nextInt(board.getWidth())).setDuration(500);
                kingPiece.animate().y(rand.nextInt(board.getWidth())).setDuration(500);
            }
        });

        black1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black1");

            }
        });

        black2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black2");

            }
        });

        black3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black3");

            }
        });

        black4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black4");

            }
        });

        black5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black5");

            }
        });

        black6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black6");

            }
        });

        black7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black7");
            }
        });

        black8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black8");
            }
        });

        black9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black9");
            }
        });

        black10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black10");
            }
        });

        black11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black11");
            }
        });

        black12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black12");
            }
        });

        black13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black13");
            }
        });

        black14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black14");
            }
        });

        black15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black15");
            }
        });

        black16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black15");
            }
        });

        black17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black16");
            }
        });

        black18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black17");
            }
        });

        black18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black18");
            }
        });

        black19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black19");
            }
        });

        black20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black20");
            }
        });

        black21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black21");
            }
        });

        black22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black22");
            }
        });

        black23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black23");
            }
        });

        black24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black24");
            }
        });

        white1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white1");
            }
        });

        white2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white2");
            }
        });

        white3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white3");
            }
        });

        white4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white4");
            }
        });

        white5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white5");
            }
        });

        white6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white6");
            }
        });


        white7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white7");
            }
        });

        white8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white8");
            }
        });


        white9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white9");
            }
        });

        white10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white10");
            }
        });

        white11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white11");
            }
        });

        white12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white12");
            }
        });


        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "Exit Pressed");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
