package com.raqib.zarbzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = 0 and 1 = x
    int activePlayer = 0;
    boolean gameIsActive = true;

    //2 means unPlayed Move

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setElevation(0);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
        counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.zarb);
                activePlayer = 0;
            }

        counter.animate().translationYBy(1000f).rotationYBy(1440).setDuration(999);


            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[1]] != 2){

                    //SOMEONE HAS WON
                    gameIsActive = false;

                    String winner = "Zarb";
                    if(gameState[winningPosition[0]] == 0){
                        winner = "Zero";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
//                    winnerMessage.setTranslationX(-1000f);
//                    playAgain.setTranslationX(-1000f);
//                    layout.animate().translationXBy(-1000f);

                    winnerMessage.setText( winner + " has won!");
//                    winnerMessage.animate().translationXBy(1000f).rotationXBy(3600).setDuration(999);
//                    playAgain.animate().translationXBy(1000f).rotationXBy(3600).setDuration(999);

                    layout.animate().rotationXBy(1800).rotationYBy(1800).setDuration(999);
                    layout.setVisibility(View.VISIBLE);

                }else{
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
//                winnerMessage.setTranslationX(-1000f);
                        winnerMessage.setText("Game Draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
//                winnerMessage.animate().translationXBy(1000f).setDuration(500);

                        layout.animate().rotationXBy(1800).setDuration(999);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void playAgain(View view) {
        reset();
    }

    public void restartGame(MenuItem item) {
        reset();
    }

    public void reset(){
        gameIsActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}