package com.bruskajp.fisttablets.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.*;
import android.widget.Button;

import java.lang.Integer;
import java.lang.String;
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
import android.widget.RelativeLayout;

import java.util.Random;

import com.bruskajp.fisttablets.R;
import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Token;

/**
 * Created by josephd on 11/16/15.
 */
public class GameBoard extends Activity{

    Button buttonExit;
    ImageView board;
    Board pieces = new Board();
    static ImageView kingPiece;
    static ImageView black1;
    static ImageView black2;
    static ImageView black3;
    static ImageView black4;
    static ImageView black5;
    static ImageView black6;
    static ImageView black7;
    static ImageView black8;
    static ImageView black9;
    static ImageView black10;
    static ImageView black11;
    static ImageView black12;
    static ImageView black13;
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

    int counterx;
    int countery;
    int top;
    int bottom;
    int psize;
    boolean editable = true;
    static int[] validx;
    static int[] validy;

    private final static String LOG_TAG = "GameBoard";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "initiated");
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String value = intent.getStringExtra("this worked");
        setContentView(R.layout.boardgame);
        board = (ImageView) findViewById(R.id.board);

        psize = (int)Math.ceil(getWindowManager().getDefaultDisplay().getWidth() / 11.0);

        int pieceSize = psize;
        kingPiece = (ImageView) findViewById(R.id.kingpiece);

        kingPiece.getLayoutParams().height = pieceSize;
        kingPiece.getLayoutParams().width = pieceSize;

        int left_side = (int)Math.ceil(psize/2.0);
        Log.i(LOG_TAG, pieceSize + "; " + left_side);
        top = (getWindowManager().getDefaultDisplay().getHeight()/2) - (getWindowManager().getDefaultDisplay().getWidth() / 2) - left_side;
        bottom = (getWindowManager().getDefaultDisplay().getHeight()/2) + (getWindowManager().getDefaultDisplay().getWidth() / 2) - left_side;
        counterx = 0;
        countery = top;

        int[] array = {0, psize, psize * 2, psize * 3, psize * 4, psize * 5, psize * 6, psize * 7, psize * 8, psize * 9, psize * 10};
        validx = array;
        int[] array1 = {top, top + psize, top + psize * 2, top + psize * 3, top + psize * 4, top + psize * 5, top + psize * 6, top + psize * 7, top + psize * 8, top + psize * 9, top + psize * 10};
        validy = array1;

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

        black1.getLayoutParams().height = pieceSize;
        black1.getLayoutParams().width = pieceSize;
        black2.getLayoutParams().height = pieceSize;
        black2.getLayoutParams().width = pieceSize;
        black3.getLayoutParams().height = pieceSize;
        black3.getLayoutParams().width = pieceSize;
        black4.getLayoutParams().height = pieceSize;
        black4.getLayoutParams().width = pieceSize;
        black5.getLayoutParams().height = pieceSize;
        black5.getLayoutParams().width = pieceSize;
        black6.getLayoutParams().height = pieceSize;
        black6.getLayoutParams().width = pieceSize;
        black7.getLayoutParams().height = pieceSize;
        black7.getLayoutParams().width = pieceSize;
        black8.getLayoutParams().height = pieceSize;
        black8.getLayoutParams().width = pieceSize;
        black9.getLayoutParams().height = pieceSize;
        black9.getLayoutParams().width = pieceSize;
        black10.getLayoutParams().height = pieceSize;
        black10.getLayoutParams().width = pieceSize;
        black11.getLayoutParams().height = pieceSize;
        black11.getLayoutParams().width = pieceSize;
        black12.getLayoutParams().height = pieceSize;
        black12.getLayoutParams().width = pieceSize;
        black13.getLayoutParams().height = pieceSize;
        black13.getLayoutParams().width = pieceSize;
        black14.getLayoutParams().height = pieceSize;
        black14.getLayoutParams().width = pieceSize;
        black15.getLayoutParams().height = pieceSize;
        black15.getLayoutParams().width = pieceSize;
        black16.getLayoutParams().height = pieceSize;
        black16.getLayoutParams().width = pieceSize;
        black17.getLayoutParams().height = pieceSize;
        black17.getLayoutParams().width = pieceSize;
        black18.getLayoutParams().height = pieceSize;
        black18.getLayoutParams().width = pieceSize;
        black19.getLayoutParams().height = pieceSize;
        black19.getLayoutParams().width = pieceSize;
        black20.getLayoutParams().height = pieceSize;
        black20.getLayoutParams().width = pieceSize;
        black21.getLayoutParams().height = pieceSize;
        black21.getLayoutParams().width = pieceSize;
        black22.getLayoutParams().height = pieceSize;
        black22.getLayoutParams().width = pieceSize;
        black23.getLayoutParams().height = pieceSize;
        black23.getLayoutParams().width = pieceSize;
        black24.getLayoutParams().height = pieceSize;
        black24.getLayoutParams().width = pieceSize;

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

        white1.getLayoutParams().height = pieceSize;
        white1.getLayoutParams().width = pieceSize;
        white2.getLayoutParams().height = pieceSize;
        white2.getLayoutParams().width = pieceSize;
        white3.getLayoutParams().height = pieceSize;
        white3.getLayoutParams().width = pieceSize;
        white4.getLayoutParams().height = pieceSize;
        white4.getLayoutParams().width = pieceSize;
        white5.getLayoutParams().height = pieceSize;
        white5.getLayoutParams().width = pieceSize;
        white6.getLayoutParams().height = pieceSize;
        white6.getLayoutParams().width = pieceSize;
        white7.getLayoutParams().height = pieceSize;
        white7.getLayoutParams().width = pieceSize;
        white8.getLayoutParams().height = pieceSize;
        white8.getLayoutParams().width = pieceSize;
        white9.getLayoutParams().height = pieceSize;
        white9.getLayoutParams().width = pieceSize;
        white10.getLayoutParams().height = pieceSize;
        white10.getLayoutParams().width = pieceSize;
        white11.getLayoutParams().height = pieceSize;
        white11.getLayoutParams().width = pieceSize;
        white12.getLayoutParams().height = pieceSize;
        white12.getLayoutParams().width = pieceSize;

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

        kingPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, "king pressed");
                movePiece(kingPiece);
            }
        });

        black1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black1");
                movePiece(black1);
            }
        });

        black2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black2");
                movePiece(black2);

            }
        });

        black3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black3");
                movePiece(black3);

            }
        });

        black4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black4");
                movePiece(black4);

            }
        });

        black5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black5");
                movePiece(black5);

            }
        });

        black6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black6");
                movePiece(black6);

            }
        });

        black7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black7");
                movePiece(black7);
            }
        });

        black8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black8");
                movePiece(black8);
            }
        });

        black9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black9");
                movePiece(black9);
            }
        });

        black10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black10");
                movePiece(black10);
            }
        });

        black11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black11");
                movePiece(black11);
            }
        });

        black12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black12");
                movePiece(black12);
            }
        });

        black13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black13");
                movePiece(black13);
            }
        });

        black14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black14");
                movePiece(black14);
            }
        });

        black15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black15");
                movePiece(black15);
            }
        });

        black16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black16");
                movePiece(black16);
            }
        });

        black17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black17");
                movePiece(black17);
            }
        });

        black18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black18");
                movePiece(black18);
            }
        });

        black19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black19");
                movePiece(black19);
            }
        });

        black20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black20");
                movePiece(black20);
            }
        });

        black21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black21");
                movePiece(black21);
            }
        });

        black22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black22");
                movePiece(black22);
            }
        });

        black23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black23");
                movePiece(black23);
            }
        });

        black24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "black24");
                movePiece(black24);
            }
        });

        white1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white1");
                movePiece(white1);
            }
        });

        white2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white2");
                movePiece(white2);
            }
        });

        white3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white3");
                movePiece(white3);
            }
        });

        white4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white4");
                movePiece(white4);
            }
        });

        white5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white5");
                movePiece(white5);
            }
        });

        white6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white6");
                movePiece(white6);
            }
        });


        white7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white7");
                movePiece(white7);
            }
        });

        white8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white8");
                movePiece(white8);
            }
        });


        white9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white9");
                movePiece(white9);
            }
        });

        white10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white10");
                movePiece(white10);
            }
        });

        white11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white11");
                movePiece(white11);
            }
        });

        white12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i(LOG_TAG, "white12");
                movePiece(white12);
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

    public void movePiece(final ImageView move){
        final RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
        editable = true;

        parent.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                Log.i(LOG_TAG, "Touch at " + ev.getX() + ", " + ev.getY());
                int xcoardinate = validx[0];
                int ycoardinate = validy[0];
                int count = 0;
                if (editable && ev.getY() > top && ev.getY() < bottom) {
                    while(ev.getX() - psize > xcoardinate && count < 11){
                        xcoardinate = validx[count++];
                    }

                    count = 0;
                    while(ev.getY() - psize > ycoardinate && count < 11){
                        ycoardinate = validy[count++];
                    }


                    Log.i(LOG_TAG, "ANIMATE!" + xcoardinate + " " + ycoardinate);
                    move.animate().x(xcoardinate).setDuration(1);
                    move.animate().y(ycoardinate).setDuration(1);
                    editable = false;
                }


                return true;
            }
        });
    }

    public static void movePieceComputer(int oldX, int oldY, int newX, int newY){

        ImageView move = null;
        move.animate().x(validx[newX]).setDuration(1);
        move.animate().y(validy[newY]).setDuration(1);
    }

}
