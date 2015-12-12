package com.bruskajp.fisttablets.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewManager;
import android.widget.Button;

import java.lang.String;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;

import com.bruskajp.fisttablets.Game.Game;
import com.bruskajp.fisttablets.Game.DemoGame;
import com.bruskajp.fisttablets.Game.LocalGame;
import com.bruskajp.fisttablets.Game.SinglePlayerGame;
import com.bruskajp.fisttablets.R;
import com.bruskajp.fisttablets.gameengine.Board;
import com.bruskajp.fisttablets.gameengine.Token;

/**
 * Created by josephd on 11/16/15.
 */
public class GameBoard extends Activity{

    Button buttonExit;
    ImageView board;
    Board pieces;
    private class PieceInfo {
        public ImageView iv;
        public int x;
        public int y;
        public boolean firstMove = true; // Used to make the human player touch work
        public PieceInfo(ImageView iv, int x, int y){
            this.iv=iv;
            this.x=x;
            this.y=y;
        }
    }

    static List<PieceInfo> piecesInfo = new LinkedList<>();

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
    static ImageView black14;
    static ImageView black15;
    static ImageView black16;
    static ImageView black17;
    static ImageView black18;
    static ImageView black19;
    static ImageView black20;
    static ImageView black21;
    static ImageView black22;
    static ImageView black23;
    static ImageView black24;
    static ImageView white1;
    static ImageView white2;
    static ImageView white3;
    static ImageView white4;
    static ImageView white5;
    static ImageView white6;
    static ImageView white7;
    static ImageView white8;
    static ImageView white9;
    static ImageView white10;
    static ImageView white11;
    static ImageView white12;

    int counterx;
    int countery;
    int top;
    int bottom;
    int psize;
    static boolean editable = true;
    static int[] validx;
    static int[] validy;

    public int lastXPos = -1;
    public int lastYPos = -1;
    public int newXPos = -1;
    public int newYPos = -1;

    public String playerColor;
    public boolean whiteTurn = false;
    public boolean blackTurn = false;

    private final static String LOG_TAG = "GameBoard";

    Intent myIntent;

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

        setBlackBoardPieces();
        setWhiteBoardPieces();


        final GameBoard gb = this;
        Thread thread;

        myIntent =  getIntent();

        playerColor = (String) myIntent.getExtras().get("color");

        if((Integer) myIntent.getExtras().get("players") == 0){
            thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    Game demoGame = new DemoGame(gb);
                }
            });
        } else if((Integer) myIntent.getExtras().get("players") == 1){
            thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    Game singlePlayerGame = new SinglePlayerGame(gb);
                }
            });
        } else {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Game localGame = new LocalGame(gb);
                }
            });
        }


        this.initializeMenu();
        thread.start();
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

    /***
     * Initializes the game board.
     */
    public void initializeMenu() {

        initializeWhitePieceListeners();
        initializeBlackPieceListeners();

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

    /***
     * Initializes the listeners for the White Tokens.
     */
    public void initializeWhitePieceListeners() {
        if ((Integer) myIntent.getExtras().get("players") == 1 || (Integer) myIntent.getExtras().get("players") == 2) {

            kingPiece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "king pressed");
                    if(whiteTurn == true) movePiece(kingPiece);
                    whiteTurn = false;
                }
            });

            white1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white1");
                    if(whiteTurn == true) movePiece(white1);
                    whiteTurn = false;
                }
            });

            white2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white2");
                    if(whiteTurn == true) movePiece(white2);
                    whiteTurn = false;
                }
            });

            white3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white3");
                    if(whiteTurn == true) movePiece(white3);
                    whiteTurn = false;
                }
            });

            white4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white4");
                    if(whiteTurn == true) movePiece(white4);
                    whiteTurn = false;
                }
            });

            white5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white5");
                    if(whiteTurn == true) movePiece(white5);
                    whiteTurn = false;
                }
            });

            white6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white6");
                    if(whiteTurn == true) movePiece(white6);
                    whiteTurn = false;
                }
            });

            white7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white7");
                    if(whiteTurn == true) movePiece(white7);
                    whiteTurn = false;
                }
            });

            white8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white8");
                    if(whiteTurn == true) movePiece(white8);
                    whiteTurn = false;
                }
            });

            white9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white9");
                    if(whiteTurn == true) movePiece(white9);
                    whiteTurn = false;
                }
            });

            white10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white10");
                    if(whiteTurn == true) movePiece(white10);
                    whiteTurn = false;
                }
            });

            white11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white11");
                    if(whiteTurn == true) movePiece(white11);
                    whiteTurn = false;
                }
            });

            white12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(LOG_TAG, "white12");
                    if(whiteTurn == true) movePiece(white12);
                    whiteTurn = false;
                }
            });
        }
    }

    /***
     * Initializes the listeners for the Black Tokens.
     */
    public void initializeBlackPieceListeners(){
        if ((Integer) myIntent.getExtras().get("players") == 1 || (Integer) myIntent.getExtras().get("players") == 2) {

            black1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black1");
                    if(blackTurn == true) movePiece(black1);
                    blackTurn = false;
                }
            });

            black2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black2");
                    if(blackTurn == true) movePiece(black2);
                    blackTurn = false;
                }
            });

            black3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black3");
                    if(blackTurn == true) movePiece(black3);
                    blackTurn = false;
                }
            });

            black4.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black4");
                    if(blackTurn == true) movePiece(black4);
                    blackTurn = false;
                }
            });

            black5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black5");
                    if(blackTurn == true) movePiece(black5);
                    blackTurn = false;
                }
            });

            black6.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black6");
                    if(blackTurn == true) movePiece(black6);
                    blackTurn = false;
                }
            });

            black7.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black7");
                    if(blackTurn == true) movePiece(black7);
                    blackTurn = false;
                }
            });

            black8.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black8");
                    if(blackTurn == true) movePiece(black8);
                    blackTurn = false;
                }
            });

            black9.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black9");
                    if(blackTurn == true) movePiece(black9);
                    blackTurn = false;
                }
            });

            black10.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black10");
                    if(blackTurn == true) movePiece(black10);
                    blackTurn = false;
                }
            });

            black11.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black11");
                    if(blackTurn == true) movePiece(black11);
                    blackTurn = false;
                }
            });

            black12.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black12");
                    if(blackTurn == true) movePiece(black12);
                    blackTurn = false;
                }
            });

            black13.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black13");
                    if(blackTurn == true) movePiece(black13);
                    blackTurn = false;
                }
            });

            black14.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black14");
                    if(blackTurn == true) movePiece(black14);
                    blackTurn = false;
                }
            });

            black15.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black15");
                    if(blackTurn == true) movePiece(black15);
                    blackTurn = false;
                }
            });

            black16.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black16");
                    if(blackTurn == true) movePiece(black16);
                    blackTurn = false;
                }
            });

            black17.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black17");
                    if(blackTurn == true) movePiece(black17);
                    blackTurn = false;
                }
            });

            black18.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black18");
                    if(blackTurn == true) movePiece(black18);
                    blackTurn = false;
                }
            });

            black19.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black19");
                    if(blackTurn == true) movePiece(black19);
                    blackTurn = false;
                }
            });

            black20.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black20");
                    if(blackTurn == true) movePiece(black20);
                    blackTurn = false;
                }
            });

            black21.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black21");
                    if(blackTurn == true) movePiece(black21);
                    blackTurn = false;
                }
            });

            black22.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black22");
                    if(blackTurn == true) movePiece(black22);
                    blackTurn = false;
                }
            });

            black23.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black23");
                    if(blackTurn == true) movePiece(black23);
                    blackTurn = false;
                }
            });

            black24.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Log.i(LOG_TAG, "black24");
                    if(blackTurn == true) movePiece(black24);
                    blackTurn = false;
                }
            });
        }
    }

    /***
     * Sets the coordinates of the movement for a human player.
     * @param move The {@code ImageView} to be used.
     */
    public void movePiece(final ImageView move){
        final RelativeLayout parent = (RelativeLayout) findViewById(R.id.parent);
        editable = true;

        int xcoardinate = validx[0];
        int ycoardinate = validy[0];

        int count = 0;
        if (editable && move.getY() > top && move.getY() < bottom) {
            while (move.getX() - psize > xcoardinate && count < 11) {
                xcoardinate = validx[count++];
            }
            lastXPos = count;

            count = 0;
            while (move.getY() - psize > ycoardinate && count < 11) {
                ycoardinate = validy[count++];
            }
            // Hack around an off by one error
            for(int i = 0 ; i <piecesInfo.size() ; ++i){
                PieceInfo pi = piecesInfo.get(i);
                if(pi.iv == move){
                    if(pi.firstMove){
                        pi.firstMove = false;
                        if(count != 0){
                            --count;
                        }
                    }
                    break;
                }
            }
            lastYPos = count;
            Log.e("GameBoard1", "  " + lastXPos + " " + lastYPos);
        }

        parent.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                Log.i(LOG_TAG, "Touch at " + ev.getX() + ", " + ev.getY());
                int xcoardinate = validx[0];
                int ycoardinate = validy[0];
                int count = 0;
                if (editable && ev.getY() > top && ev.getY() < bottom) {
                    while (ev.getX() - psize > xcoardinate && count < 11) {
                        xcoardinate = validx[count++];
                    }
                    if(count != 0){
                        --count;
                    }
                    newXPos = count;

                    count = 0;
                    while (ev.getY() - psize > ycoardinate && count < 11) {
                        ycoardinate = validy[count++];
                    }
                    if(count != 0){
                        --count;
                    }
                    newYPos = count;
                    Log.e("GameBoard2", "  " + newXPos + " " + newYPos);

                    editable = false;
                }

                return true;
            }
        });
    }

    /***
     * Moves a Token on the visual display.
     * @param oldX The {@code int} to describe the moves old xPosition.
     * @param oldY The {@code int} to describe the moves old yPosition.
     * @param newX The {@code int} to describe the moves new xPosition.
     * @param newY The {@code int} to describe the moves new yPosition.
     * @param deletedToks The {@code LinkedList<Token>} to describe the deleted Tokens.
     */
    public void movePieceComputer(int oldX, int oldY, int newX, int newY, LinkedList<Token> deletedToks){
        for(int i = 0 ; i < piecesInfo.size(); ++i){
            final PieceInfo pi = piecesInfo.get(i);
            if(pi.x==validx[oldX]&&pi.y==validy[oldY]){
                pi.x=validx[newX];
                pi.y=validy[newY];
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        (pi.iv).animate().x(pi.x).setDuration(1);
                        (pi.iv).animate().y(pi.y).setDuration(1);
                    }
                });
                break;
            }
        }
        for(Token tok: deletedToks){
            int x = tok.getxPosition();
            int y = tok.getyPosition();
            for(int i = 0 ; i < piecesInfo.size(); ++i){
                final PieceInfo pi = piecesInfo.get(i);
                if(pi.x==validx[x] && pi.y==validy[y]){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pi.iv.setOnClickListener(null);
                            pi.iv.setVisibility(View.INVISIBLE);
                        }
                    });
                    piecesInfo.remove(pi);
                    break;
                }
            }
        }
    }

    /***
     * Initializes pieceInfo for each black piece.
     */
    private void setBlackBoardPieces(){
        piecesInfo.add(new PieceInfo(black1,validx[3],validy[0]));
        piecesInfo.add(new PieceInfo(black2,validx[4],validy[0]));
        piecesInfo.add(new PieceInfo(black3,validx[5],validy[0]));
        piecesInfo.add(new PieceInfo(black4,validx[6],validy[0]));
        piecesInfo.add(new PieceInfo(black5,validx[7],validy[0]));

        piecesInfo.add(new PieceInfo(black6,validx[5],validy[1]));

        piecesInfo.add(new PieceInfo(black7,validx[0],validy[3]));
        piecesInfo.add(new PieceInfo(black8,validx[10],validy[3]));

        piecesInfo.add(new PieceInfo(black9,validx[0],validy[4]));
        piecesInfo.add(new PieceInfo(black10,validx[10],validy[4]));

        piecesInfo.add(new PieceInfo(black11,validx[0],validy[5]));
        piecesInfo.add(new PieceInfo(black12,validx[1],validy[5]));
        piecesInfo.add(new PieceInfo(black13,validx[9],validy[5]));
        piecesInfo.add(new PieceInfo(black14,validx[10],validy[5]));

        piecesInfo.add(new PieceInfo(black15,validx[0],validy[6]));
        piecesInfo.add(new PieceInfo(black16,validx[10],validy[6]));

        piecesInfo.add(new PieceInfo(black17,validx[0],validy[7]));
        piecesInfo.add(new PieceInfo(black18,validx[10],validy[7]));

        piecesInfo.add(new PieceInfo(black19,validx[5],validy[9]));

        piecesInfo.add(new PieceInfo(black20,validx[3],validy[10]));
        piecesInfo.add(new PieceInfo(black21,validx[4],validy[10]));
        piecesInfo.add(new PieceInfo(black22,validx[5],validy[10]));
        piecesInfo.add(new PieceInfo(black23,validx[6],validy[10]));
        piecesInfo.add(new PieceInfo(black24,validx[7],validy[10]));
    }

    /***
     * Initializes pieceInfo for each black piece.
     */
    private void setWhiteBoardPieces() {

        piecesInfo.add(new PieceInfo(white1,validx[5],validy[3]));

        piecesInfo.add(new PieceInfo(white2,validx[4],validy[4]));
        piecesInfo.add(new PieceInfo(white3,validx[5],validy[4]));
        piecesInfo.add(new PieceInfo(white4,validx[6],validy[4]));

        piecesInfo.add(new PieceInfo(white5,validx[3],validy[5]));
        piecesInfo.add(new PieceInfo(white6,validx[4],validy[5]));
        piecesInfo.add(new PieceInfo(kingPiece,validx[5],validy[5]));
        piecesInfo.add(new PieceInfo(white7,validx[6],validy[5]));
        piecesInfo.add(new PieceInfo(white8,validx[7],validy[5]));

        piecesInfo.add(new PieceInfo(white9,validx[4],validy[6]));
        piecesInfo.add(new PieceInfo(white10,validx[5],validy[6]));
        piecesInfo.add(new PieceInfo(white11,validx[6],validy[6]));

        piecesInfo.add(new PieceInfo(white12,validx[5],validy[7]));

    }
}
