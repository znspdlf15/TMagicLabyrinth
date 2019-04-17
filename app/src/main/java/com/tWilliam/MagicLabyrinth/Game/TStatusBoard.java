package com.tWilliam.MagicLabyrinth.Game;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import com.tWilliam.MagicLabyrinth.R;

public class TStatusBoard extends ConstraintLayout {
    private ConstraintLayout leftLayout;
    private ConstraintLayout centerLayout;
    private ConstraintLayout rightLayout;

    private final int leftId = 1001;
    private final int centerId = 1002;
    private final int rightId = 1003;

    private TDice mDice;

    public TStatusBoard(Context context) {
        super(context);

        leftLayout = new ConstraintLayout(this.getContext());
        leftLayout.setId(leftId);
        centerLayout = new ConstraintLayout(this.getContext());
        centerLayout.setId(centerId);
        rightLayout = new ConstraintLayout(this.getContext());
        rightLayout.setId(rightId);

        ConstraintLayout.LayoutParams statusParams = new ConstraintLayout.LayoutParams(0, 0);
        statusParams.bottomToBottom = this.getId();
        statusParams.leftToLeft = this.getId();
        statusParams.rightToLeft = centerId;
        statusParams.topToTop = this.getId();
        leftLayout.setLayoutParams(statusParams);
        leftLayout.setBackgroundColor(Color.parseColor("#ff0000"));
        this.addView(leftLayout);

        statusParams = new ConstraintLayout.LayoutParams(0, 0);
        statusParams.bottomToBottom = this.getId();
        statusParams.leftToRight = leftId;
        statusParams.rightToLeft = rightId;
        statusParams.topToTop = this.getId();
        centerLayout.setLayoutParams(statusParams);
        centerLayout.setBackgroundColor(Color.parseColor("#00ff00"));

        this.addView(centerLayout);

        statusParams = new ConstraintLayout.LayoutParams(0, 0);
        statusParams.bottomToBottom = this.getId();
        statusParams.leftToRight = centerId;
        statusParams.rightToRight = this.getId();
        statusParams.topToTop = this.getId();
        rightLayout.setLayoutParams(statusParams);
        rightLayout.setBackgroundColor(Color.parseColor("#0000ff"));

        this.addView(rightLayout);

        mDice = new TDice(centerLayout.getContext());
        statusParams = new ConstraintLayout.LayoutParams(0, 0);
        statusParams.bottomToBottom = centerId;
        statusParams.leftToLeft = centerId;
        statusParams.rightToRight = centerId;
        statusParams.topToTop = centerId;
        rightLayout.setLayoutParams(statusParams);

        mDice.setImageResource(R.mipmap.dice_0);
        centerLayout.addView(mDice);
    }

    public void setAllOnClickListner(View.OnClickListener listener){

    }
    public void reactOnClick(View v){

    }
}
