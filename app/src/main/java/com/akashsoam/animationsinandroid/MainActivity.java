package com.akashsoam.animationsinandroid;

import static com.akashsoam.animationsinandroid.MainActivity.Player.NoOne;
import static com.akashsoam.animationsinandroid.MainActivity.Player.ONE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    enum Player {ONE, TWO, NoOne}

    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[]{NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne};

    int[][] winnerRowsAndColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;
    private Button btnReset;

    GridLayout mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReset = findViewById(R.id.btnReset);
        mGridLayout = findViewById(R.id.gridLayout);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });
//        if (isFull()) {
//            Toast.makeText(this, "No WINNER", Toast.LENGTH_SHORT).show();
//            resetTheGame();
//        }

    }

    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int imageViewTappedTag = Integer.parseInt(imageView.getTag().toString());
        if (playerChoices[imageViewTappedTag] == NoOne && gameOver == false) {
            tappedImageView.setTranslationX(-2000);
            playerChoices[imageViewTappedTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.rabbit);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tortoise);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(360 * 3).setDuration(1000);
            Toast.makeText(this, tappedImageView.getTag().toString() + "", Toast.LENGTH_SHORT).show();


            for (int[] winnerCols : winnerRowsAndColumns) {
                if ((playerChoices[winnerCols[0]] == playerChoices[winnerCols[1]] && playerChoices[winnerCols[1]] == playerChoices[winnerCols[2]] && playerChoices[winnerCols[0]] != NoOne)) {
                    gameOver = true;
                    btnReset.setVisibility(View.VISIBLE);
                    if (currentPlayer == ONE) {
                        Toast.makeText(this, "We have a WINNER -player2", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(this, "We have a WINNER -player1", Toast.LENGTH_LONG).show();

                    }
                }
            }
        }

    }

    //reset game function
    private void resetTheGame() {
        for (int index = 0; index < mGridLayout.getChildCount(); ++index) {
            ImageView imageView = (ImageView) mGridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.3f);
        }
        this.currentPlayer = ONE;
        playerChoices = new Player[]{NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne, NoOne};
        gameOver = false;
        btnReset.setVisibility(View.GONE);
    }

//    boolean isFull() {
//        boolean isFull = false;
//        for (int i = 0; i < mGridLayout.getChildCount(); i++) {
//            ImageView iv = (ImageView) mGridLayout.getChildAt(i);
//            if (iv.getDrawable() != null) {
//                isFull = true;
//                break;
//            }
//        }
//        return isFull;
//    }
}
