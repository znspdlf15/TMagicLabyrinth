package com.tWilliam.MagicLabyrinth.Activities.PopUp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.tWilliam.MagicLabyrinth.R;
import com.tWilliam.MagicLabyrinth.TLibrary.TActivityConstant;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;

public class AiLevelSelectActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
    private Button selectButton;
    private Button cancelButton;
    private Button hardButton;
    private Button mediumButton;
    private Button easyButton;

    private int level;
    private int goal_count;

    private TextView levelInstrcution;

    private NumberPicker number_of_wall_picker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ai_level_select);

        selectButton = findViewById(R.id.ai_level_select_button);
        cancelButton = findViewById(R.id.ai_level_cancel_button);

        hardButton = findViewById(R.id.ai_level_select_1_button);
        mediumButton = findViewById(R.id.ai_level_select_2_button);
        easyButton = findViewById(R.id.ai_level_select_3_button);

        mediumButton.setPressed(true);
        level = 2;

        levelInstrcution = findViewById(R.id.ai_level_select_instruction);
        levelInstrcution.setText(R.string.ai_2_instruction);

        number_of_wall_picker = findViewById(R.id.number_of_wall_picker);

        number_of_wall_picker.setMinValue(TConstant.MIN_WALL_COUNT);
        number_of_wall_picker.setMaxValue(TConstant.MAX_WALL_COUNT);
        number_of_wall_picker.setValue(TConstant.MAX_WALL_COUNT);

        hardButton.setOnTouchListener(this);
        mediumButton.setOnTouchListener(this);
        easyButton.setOnTouchListener(this);
        hardButton.performClick();
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
                intent.putExtra("ai_level", level);
                intent.putExtra("goal_count", goal_count);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_UP ){
            switch ( v.getId() ){
                case R.id.ai_level_select_1_button:
                    level = 1;
                    hardButton.setPressed(true);
                    mediumButton.setPressed(false);
                    easyButton.setPressed(false);
                    levelInstrcution.setText(R.string.ai_1_instruction);
                    break;
                case R.id.ai_level_select_2_button:
                    level = 2;
                    hardButton.setPressed(false);
                    mediumButton.setPressed(true);
                    easyButton.setPressed(false);
                    levelInstrcution.setText(R.string.ai_2_instruction);
                    break;
                case R.id.ai_level_select_3_button:
                    level = 3;
                    hardButton.setPressed(false);
                    mediumButton.setPressed(false);
                    easyButton.setPressed(true);
                    levelInstrcution.setText(R.string.ai_3_instruction);
                    break;
                default:
            }
        }
        return true;
    }
}
