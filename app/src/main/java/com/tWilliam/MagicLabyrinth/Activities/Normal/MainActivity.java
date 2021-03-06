package com.tWilliam.MagicLabyrinth.Activities.Normal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tWilliam.MagicLabyrinth.R;

public class MainActivity extends StandardActivity implements View.OnClickListener {
    private int[] ClickEventItems = {
            R.id.bt_normal_game,
            R.id.bt_multi_game,
            R.id.bt_online_game
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for( int clickItem: ClickEventItems ){
            findViewById(clickItem).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch ( view.getId() ){
            case R.id.bt_normal_game:
                intent = new Intent(this, SingleGameActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_multi_game:
                intent = new Intent(this, DoubleGameActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_online_game:
                intent = new Intent(this, OnlineGameActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
