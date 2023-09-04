package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    boolean  playerOneActive;
    private TextView playerOneScore,playerTwoScore,playerStatus;
    private final Button[] buttons =new Button[9];
    private Button reset,playagain;
    private  int playerOneScoreCount, playerTwoScoreCount;
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions= {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6} };
    int rounds;
    private Object view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerOneScore = findViewById(R.id.playerone);
        playerTwoScore = findViewById(R.id.playertwo);
        playerStatus = findViewById(R.id.status);
        reset= findViewById(R.id.reset);
        playagain = findViewById(R.id.playagain);
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds= 0;

    }

    @Override
    public void onClick(View v) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        else if(checkWinner()){
            return;
        }
        String buttonID = ((Button) view).getResources().getResourceEntryName(((Button) view).getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if(playerOneActive) {
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Integer.parseInt("#ffc34a"));
            gameState[gameStatePointer] =0;
        }
        else {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Integer.parseInt("70fc3a"));
            gameState[gameStatePointer] =1;
        }
        rounds++;
        if(checkWinner()){
            if(playerOneActive){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-1 has won");
            }
            else {
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("Player-2 has won");
            }
        }
        else if(rounds==9){
            playerStatus.setText("No Winner");
        }
        else {
            playerOneActive = !playerOneActive;
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playagain();
                playerOneScoreCount=0;
                playerTwoScoreCount=0;
            }
        });{
            playagain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playagain();
                }
            });
        }
    }

    private void playagain() {
        rounds =0;
        playerOneActive =true;
        for(int i=0; i< buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
        playerStatus.setText("Status");
    }
    private void updatePlayerScore() {
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPositions : winningPositions){
            if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                    gameState[winningPositions[0]] != 2) {
                winnerResults = true;
                break;
            }
        }
        return winnerResults;
    }
}