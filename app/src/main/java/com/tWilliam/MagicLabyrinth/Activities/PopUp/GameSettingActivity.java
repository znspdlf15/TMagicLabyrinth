package com.tWilliam.MagicLabyrinth.Activities.PopUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;

public class GameSettingActivity extends Activity implements View.OnClickListener {
    private Button selectButton;
    private Button cancelButton;

    private com.shawnlin.numberpicker.NumberPicker number_of_wall_picker;
    private com.shawnlin.numberpicker.NumberPicker goal_score_picker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_setting_select);

        selectButton = findViewById(R.id.ai_level_select_button);
        cancelButton = findViewById(R.id.ai_level_cancel_button);

        number_of_wall_picker = findViewById(R.id.number_of_wall_picker);

        number_of_wall_picker.setMinValue(TConstant.MIN_WALL_COUNT);
        number_of_wall_picker.setMaxValue(TConstant.MAX_WALL_COUNT);
        number_of_wall_picker.setValue(TConstant.MAX_WALL_COUNT);

        goal_score_picker = findViewById(R.id.goal_score_picker);
        goal_score_picker.setMaxValue(TConstant.MAX_GOAL_COUNT);
        goal_score_picker.setMinValue(TConstant.MIN_GOAL_COUNT);
        goal_score_picker.setValue(TConstant.DEFAULT_GOAL_COUNT);

        selectButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch ( v.getId() ){
            case R.id.ai_level_select_button:
                intent = new Intent();
                intent.putExtra("result", TActivityConstant.ActivityReactType.NONE);
                intent.putExtra("wall_count", number_of_wall_picker.getValue());
                intent.putExtra("goal_count", goal_score_picker.getValue());
                setResult(RESULT_OK, intent);

                finish();
                break;
            case R.id.ai_level_cancel_button:
                intent = new Intent();
                intent.putExtra("result", TActivityConstant.ActivityReactType.DESTROY);
                setResult(RESULT_CANCELED, intent);

                finish();
                break;
            default:
        }
    }
}
