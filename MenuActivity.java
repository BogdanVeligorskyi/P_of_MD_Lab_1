package ua.cn.cpnu.pmp_lab_1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Spinner;

import ua.cn.cpnu.pmp_lab_1.R;
import ua.cn.cpnu.pmp_lab_1.databinding.ActivityMenuBinding;
import ua.cn.cpnu.pmp_lab_1.model.Options;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private static final String KEY_OPTIONS = "OPTIONS";
    private static final int OPTIONS_REQUEST_CODE = 1;
    private Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            options = savedInstanceState.getParcelable("OPTIONS");
        } else
            options = new Options(5,false);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.startButton)
                .setOnClickListener(button -> {
                    Intent intent = new Intent(this, PlayActivity.class);
                    startActivity(intent);
                });

        findViewById(R.id.optionsButton)
                .setOnClickListener(button -> {
                    Intent intent = new Intent(this, OptionsActivity.class);
                    intent.putExtra(OptionsActivity.EXTRA_OPTIONS, options);
                    startActivityForResult(intent, OPTIONS_REQUEST_CODE);
                });

        findViewById(R.id.quitButton)
                .setOnClickListener(button -> {
                    finish();
                });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPTIONS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            options = data.getParcelableExtra(OptionsActivity.EXTRA_OPTIONS);
            if (options == null) {
                throw new IllegalArgumentException("Can`t get updated data from options activity");
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_OPTIONS, options);
    }

}
