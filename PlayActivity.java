package ua.cn.cpnu.pmp_lab_1.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ua.cn.cpnu.pmp_lab_1.R;

public class PlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        findViewById(R.id.quitPlay)
                .setOnClickListener(button -> {
                    finish();
                });
    }
}
