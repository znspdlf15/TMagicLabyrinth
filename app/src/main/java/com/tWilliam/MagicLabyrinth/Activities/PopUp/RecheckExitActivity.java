package com.tWilliam.MagicLabyrinth.Activities.PopUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;

public class RecheckExitActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_recheck_exit);

        Button exitButton = findViewById(R.id.exit_popup_ok_button);
        Button cancelButton = findViewById(R.id.exit_popup_cancel_button);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", TActivityConstant.ActivityReactType.DESTROY);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", TActivityConstant.ActivityReactType.NONE);
                setResult(RESULT_CANCELED, intent);

                finish();
            }
        });
    }
}
