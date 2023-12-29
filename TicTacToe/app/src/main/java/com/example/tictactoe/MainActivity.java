package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive= true;
    //Player representations
    //0-X
    //1-O
    int activePlayer= 0;
    int[] gameState= {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //gameState meanings
    //0- X
    //1-O
    //2- Empty/ null
    int [][] win= {{0,1,2},{3,4,5},{6,7,8},
                   {0,3,6},{1,4,7},{2,5,8},
                   {0,4,8},{2,4,6}};


    public void playerTap(View view){
        ImageView img= (ImageView)view;
        int tapped= Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if(gameState[tapped]==2 ) {
            gameState[tapped] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.xtictactoe);
                activePlayer = 1;
                TextView status= findViewById(R.id.status);
                status.setText("O's Turn- Tap to Play");
            } else {
                img.setImageResource(R.drawable.otictactoe);
                activePlayer = 0;
                TextView status= findViewById(R.id.status);
                status.setText("X's Turn- Tap to Play");

            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if winner
        for (int[] winPosition: win) {
            if(gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                //finding out who has won
                String winnerStr;
                gameActive= false;
                if (gameState[winPosition[0]]==0){
                    winnerStr= "X has won!!";

                }
                else{
                    winnerStr= "O has won!!";
                }
                //update the status bar for winning
                TextView status= findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        //check for draw
            if(gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 &&gameState[4]!=2 &&gameState[5]!=2&& gameState[6]!=2 && gameState[7]!=2 && gameState[8]!=2 ){
                //finding out who has won
                String drawStr;
                gameActive= false;
                drawStr= "Game Draw!";
                //update the status bar for winning
                TextView status= findViewById(R.id.status);
                status.setText(drawStr);
            }

    }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for (int i = 0; i < gameState.length; i++) {
         gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status= findViewById(R.id.status);
        status.setText("X's Turn- Tap to Play");
    }

    public void onReset(View view){
        gameReset(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}