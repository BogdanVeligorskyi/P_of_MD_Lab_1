package ua.cn.cpnu.pmp_lab_1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

// class for options activity
public class OptionsActivity extends AppCompatActivity {

    // special constant for reading settings and link to options object
    public static final String EXTRA_OPTIONS = "EXTRA_OPTIONS";
    private Options options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            options = savedInstanceState.getParcelable("OPTIONS");
        } else {
            options = getIntent().getParcelableExtra(EXTRA_OPTIONS);
        }
        setContentView(R.layout.activity_options);

        setupGroupsSpinner();
        setupCheckBox();

        // if 'Ok' button was clicked
        findViewById(R.id.ok)
                .setOnClickListener(button -> {
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_OPTIONS, options);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                });

        // if 'cancel' button was clicked
        findViewById(R.id.cancel)
                .setOnClickListener(button -> {
                    finish();
                });

    }

    // setup of checkbox
    private void setupCheckBox() {
        CheckBox is_hint_available = findViewById(R.id.hint_availability);
        is_hint_available.setChecked(options.getIs_hint_available());
        is_hint_available.setOnClickListener(v -> {
            options.setIs_hint_available(((CheckBox) v).isChecked());
        });
    }

    // setup spinner with number of questions
    private void setupGroupsSpinner() {
        Spinner questionsNumSpinner = findViewById(R.id.questions_spinner);
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
        assert questionsNumSpinner != null;
        questionsNumSpinner.setSelection(options.getNumber_of_questions() - 5);

        questionsNumSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // if number of questions was selected
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    String item_value = Options.QUESTIONS_NUM.get(i);
                    options.setNumber_of_questions(Integer.parseInt(item_value));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    // saving current state
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("OPTIONS", options);
    }

}
