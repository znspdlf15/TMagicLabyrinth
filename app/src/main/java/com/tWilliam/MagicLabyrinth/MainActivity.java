package com.tWilliam.MagicLabyrinth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        switch ( view.getId() ){
            case R.id.bt_normal_game:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_multi_game:
                break;
            case R.id.bt_online_game:
                break;

            default:
                break;
        }
    }
}
