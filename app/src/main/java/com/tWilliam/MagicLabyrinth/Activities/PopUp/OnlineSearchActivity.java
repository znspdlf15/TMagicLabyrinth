package com.tWilliam.MagicLabyrinth.Activities.PopUp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.tWilliam.MagicLabyrinth.R;

public class OnlineSearchActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_online_search_popup);

        setResult(RESULT_OK);
        finish();
    }
}
