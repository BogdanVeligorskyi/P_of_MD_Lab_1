package ua.cn.cpnu.pmp_lab_1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ua.cn.cpnu.pmp_lab_1.R;
import ua.cn.cpnu.pmp_lab_1.model.Options;
import ua.cn.cpnu.pmp_lab_1.model.Questions;

public class PlayActivity extends AppCompatActivity {

    public static final String EXTRA_OPTIONS = "EXTRA_OPTIONS";
    private Options options;
    private int current_question_num;
    private boolean is_correct;
    Questions[] arrQuestions = new Questions[10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_question_num = 1;
        is_correct = true;
        if (savedInstanceState != null) {
            options = savedInstanceState.getParcelable("OPTIONS");
        } else {
            options = getIntent().getParcelableExtra(EXTRA_OPTIONS);
            //options = new Options(5,false);
        }
        setContentView(R.layout.activity_play);
        startSetup();

        findViewById(R.id.quitPlay)
                .setOnClickListener(button -> {
                    finish();
                });

        findViewById(R.id.button_1).setOnClickListener(button -> {
            if (arrQuestions[current_question_num].var_1.equalsIgnoreCase(arrQuestions[current_question_num].answer)) {
                current_question_num += 1;
                setupQuestionsNum();
                setupQuestionPrice();
                setupCurrentQuestion();
                setupButtons(current_question_num);
            } else {
                finish();
            }
        });

        findViewById(R.id.button_2).setOnClickListener(button -> {
            if (arrQuestions[current_question_num].var_2.equalsIgnoreCase(arrQuestions[current_question_num].answer)) {
                current_question_num += 1;
                setupQuestionsNum();
                setupQuestionPrice();
                setupCurrentQuestion();
                setupButtons(current_question_num);
            } else {
                finish();
            }
        });

        findViewById(R.id.button_3).setOnClickListener(button -> {
            if (arrQuestions[current_question_num].var_3.equalsIgnoreCase(arrQuestions[current_question_num].answer)) {
                current_question_num += 1;
                setupQuestionsNum();
                setupQuestionPrice();
                setupCurrentQuestion();
                setupButtons(current_question_num);
            } else {
                finish();
            }
        });

        findViewById(R.id.button_4).setOnClickListener(button -> {
            if (arrQuestions[current_question_num].var_4.equalsIgnoreCase(arrQuestions[current_question_num].answer)) {
                current_question_num += 1;
                setupQuestionsNum();
                setupQuestionPrice();
                setupCurrentQuestion();
                setupButtons(current_question_num);
            } else {
                finish();
            }
        });

    }

    private void startSetup() {
        initializeQuestions();
        setupCheckBox();
        setupButtons(current_question_num);
        setupQuestionsNum();
        setupQuestionPrice();
        setupCurrentQuestion();
    }

    private void setupButtons(int current_question_num) {
        Button but1 = findViewById(R.id.button_1);
        Button but2 = findViewById(R.id.button_2);
        Button but3 = findViewById(R.id.button_3);
        Button but4 = findViewById(R.id.button_4);
        but1.setText(arrQuestions[current_question_num].var_1);
        but2.setText(arrQuestions[current_question_num].var_2);
        but3.setText(arrQuestions[current_question_num].var_3);
        but4.setText(arrQuestions[current_question_num].var_4);
        but1.setEnabled(true);
        but2.setEnabled(true);
        but3.setEnabled(true);
        but4.setEnabled(true);
    }

    private void setupCurrentQuestion() {
        TextView tvQuestionText = findViewById(R.id.text_of_question);
        tvQuestionText.setText(arrQuestions[current_question_num].text_of_question);
    }

    private void setupQuestionPrice() {
        TextView tvPrice  = findViewById(R.id.price);
        int question_price = current_question_num * 100000;
        tvPrice.setText("$" + String.valueOf(question_price));
    }

    private void setupQuestionsNum() {
        TextView tvQuestions = findViewById(R.id.question_number);
        tvQuestions.setText("Question " + String.valueOf(current_question_num) + " of " + options.number_of_questions);
    }

    private void setupCheckBox() {
        CheckBox cb = findViewById(R.id.is_hint);
        if (options.is_hint_available == false) {
            cb.setEnabled(false);
        } else {
            cb.setEnabled(true);
        }

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    cb.setEnabled(false);
                    int counter_incorrect_answers = 0;
                    while(true) {
                        Button but = findViewById(R.id.button_1);
                        if (but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num].answer) == false) {
                            Log.i("TAG: ", "answer=" + arrQuestions[current_question_num].answer);
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                        }
                        but = findViewById(R.id.button_2);
                        if (but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num].answer) == false) {
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                            if (counter_incorrect_answers >= 2) {
                                break;
                            }
                        }
                        but = findViewById(R.id.button_3);
                        if (but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num].answer) == false) {
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                            if (counter_incorrect_answers >= 2) {
                                break;
                            }
                        }
                        but = findViewById(R.id.button_4);
                        if (but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num].answer) == false) {
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                            if (counter_incorrect_answers >= 2) {
                                break;
                            }
                        }
                    }
                } else
                    cb.setEnabled(true);
            }
        });
    }

    private void initializeQuestions() {
        arrQuestions[0] = new Questions("Where was the Internet invented?", "United Kingdom", "China", "USA", "Soviet Union", "USA");
        arrQuestions[1] = new Questions("How many football teams take part in EURO competitions?", "64", "32", "24", "16", "24");
        arrQuestions[2] = new Questions("Where is located the shortest metro path in the world?", "Ukraine, Dnipro", "Germany, Aachen", "Italy, Milan", "Austria, Wien", "Ukraine, Dnipro");
        arrQuestions[3] = new Questions("The capital of Peru is ...", "Cusco", "Piura", "Lima", "Tarapoto", "Lima");
        arrQuestions[4] = new Questions("The longest national anthem is in ... ", "France", "Spain", "Sweden", "Greece", "Greece");
        arrQuestions[5] = new Questions("The Earth was formed ... billion years ago", "14.9", "1.1", "4.3", "10.1", "4.3");
        arrQuestions[6] = new Questions("Harry Potter series consists of ... books", "6", "7", "9", "5", "7");
        arrQuestions[7] = new Questions("When Scorpions song 'Wind of Change' was written?", "1990", "1997", "1984", "1993", "1990");
        arrQuestions[0] = new Questions("First F1 championship was held in ... ", "1975", "1947", "1950", "1960", "1950");
        arrQuestions[9] = new Questions("The speed of sound equals", "340 m/s", "500 m/s", "220 m/s", "600m/s", "340 m/s");
        List<Questions> list = Arrays.asList(arrQuestions);
        Collections.shuffle(list);
    }

}
