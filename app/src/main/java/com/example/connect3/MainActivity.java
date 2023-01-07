package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    yellow=0,red=0,empty=2;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winning={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] element : winning) {
                String winner = " ";
                if (gameState[element[0]] == gameState[element[1]] && gameState[element[1]] == gameState[element[2]] && gameState[element[0]] != 2) {
                    gameActive=false;
                    if (activePlayer == 1) {
                        winner = "YELLOW";
                    } else {
                        winner = "RED";
                    }
                    TextView text=findViewById(R.id.textView);
                    Button button=findViewById(R.id.button);
                    text.setText(winner + " has won");
                    text.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void change(View view){
        TextView text=findViewById(R.id.textView);
        Button button=findViewById(R.id.button);
        text.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        GridLayout grid=(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView counter=(ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
         activePlayer=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}