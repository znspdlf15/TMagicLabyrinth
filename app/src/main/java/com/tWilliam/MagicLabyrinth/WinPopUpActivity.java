package com.tWilliam.MagicLabyrinth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;

public class WinPopUpActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_win_popup);

        Intent intent = getIntent();

        int imageId = intent.getIntExtra("imageId", 0);

        ImageView imageView = findViewById(R.id.winner_popup_image);
        imageView.setImageResource(imageId);

        Button button = findViewById(R.id.winner_popup_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", TActivityConstant.ActivityReactType.DESTROY);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
