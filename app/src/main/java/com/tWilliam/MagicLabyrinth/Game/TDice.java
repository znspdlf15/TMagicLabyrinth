package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;

import java.util.Random;

public class TDice extends TDraw {
    public interface DiceCallBack{
        public void diceRollEnd(int retNum);
    }
    public TDice(Context context) {
        super(context);
        this.setImageResource(R.mipmap.dice_0);
    }
    public void roll(DiceCallBack callBack){
        Handler handler = new Handler();

        final DiceCallBack innerCallBack = callBack;

        for ( int i = 1; i <= 9; i++ ) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Random randomGenerator = new Random();
                    int index = randomGenerator.nextInt(TConstant.IMAGE_FOR_DICE_FILE_NAME.length);
                    int imageId = TConstant.IMAGE_FOR_DICE_FILE_NAME[index];
                    setImageResource(imageId);
                }
            }, 100 * i);
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(TConstant.IMAGE_FOR_DICE_FILE_NAME.length);
                int imageId = TConstant.IMAGE_FOR_DICE_FILE_NAME[index];
                setImageResource(imageId);

                int retNum = -1;
                if ( imageId == R.mipmap.dice_1_b ){
                    retNum = 1;
                } else if ( imageId == R.mipmap.dice_2_b || imageId == R.mipmap.dice_2a_b ){
                    retNum = 2;
                } else if ( imageId == R.mipmap.dice_3_b || imageId == R.mipmap.dice_3a_b ){
                    retNum = 3;
                }

                innerCallBack.diceRollEnd(retNum);
            }
        }, 1000);
    }
}
