package ua.cn.cpnu.pmp_lab_1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ua.cn.cpnu.pmp_lab_1.R;
import ua.cn.cpnu.pmp_lab_1.model.Options;

public class OptionsActivity extends AppCompatActivity {

    public static final String EXTRA_OPTIONS = "EXTRA_OPTIONS";
    private Options options;

    private Spinner questionsNumSpinner;
    private CheckBox isHintAvailable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            options = savedInstanceState.getParcelable("OPTIONS");
        } else {
            options = getIntent().getParcelableExtra(EXTRA_OPTIONS);
            //options = new Options(5,false);
        }
        setContentView(R.layout.activity_options);

        Log.i("QuestionsActivity start", "_num:" + options.number_of_questions);
        Log.i("IsHintActivity start", "_bool:" + options.is_hint_available);

        setupGroupsSpinner();
        setupCheckBox();

        findViewById(R.id.ok)
                .setOnClickListener(button -> {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_OPTIONS, options);
                    Log.i("Options", "_num:" + options.number_of_questions);
                    Log.i("IsHint", "_bool:" + options.is_hint_available);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                });

        findViewById(R.id.cancel)
                .setOnClickListener(button -> {
                    finish();
                });

    }

    private void setupCheckBox() {
        this.isHintAvailable = findViewById(R.id.hintAvailability);
        isHintAvailable.setChecked(options.is_hint_available);
        isHintAvailable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    options.is_hint_available = true;
                } else
                    options.is_hint_available = false;
            }
        });
    }

    private void setupGroupsSpinner() {
        this.questionsNumSpinner = findViewById(R.id.questionsSpinner);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
            R.layout.item_group,
            Options.QUESTIONS_NUM
        );
        adapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        if (questionsNumSpinner != null) {
            questionsNumSpinner.setAdapter(adapter);
        }
        questionsNumSpinner.setSelection(options.number_of_questions - 5);

        questionsNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    String item_value = Options.QUESTIONS_NUM.get(i).toString();
                    options.number_of_questions = Integer.valueOf(item_value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("OPTIONS", options);
    }

}
