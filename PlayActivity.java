package ua.cn.cpnu.pmp_lab_1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ua.cn.cpnu.pmp_lab_1.R;
import ua.cn.cpnu.pmp_lab_1.model.Options;
import ua.cn.cpnu.pmp_lab_1.model.Questions;

public class PlayActivity extends AppCompatActivity {

    public static final String EXTRA_OPTIONS = "EXTRA_OPTIONS";
    private Options options;
    private int current_question_num;
    private boolean is_first_question_answered;
    private boolean is_clicked_checkbox;
    private Questions[] arrQuestions = new Questions[10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        current_question_num = 1;
        is_first_question_answered = false;
        is_clicked_checkbox = false;
        if (savedInstanceState != null) {
            options = savedInstanceState.getParcelable("OPTIONS");
            current_question_num = savedInstanceState.getInt("CURRENT_QUESTION_NUM");
            is_first_question_answered = savedInstanceState.getBoolean("IS_FIRST_QUESTION_ANSWERED");
            is_clicked_checkbox = savedInstanceState.getBoolean("IS_CLICKED_CHECKBOX");
            arrQuestions = (Questions[]) savedInstanceState.getParcelableArray("QUESTIONS");
        } else {
            options = getIntent().getParcelableExtra(EXTRA_OPTIONS);
            initializeQuestions();
            //options = new Options(5,false);
        }
        Log.i("TAG", "current_question_num " + current_question_num);

        setContentView(R.layout.activity_play);
        startSetup();

        findViewById(R.id.quitPlay)
                .setOnClickListener(button -> showQuitDialog());

        // if you pressed button_1 - check it
        findViewById(R.id.button_1).setOnClickListener(button -> checkAnswer(0));

        // if you pressed button_2 - check it
        findViewById(R.id.button_2).setOnClickListener(button -> checkAnswer(1));

        // if you pressed button_3 - check it
        findViewById(R.id.button_3).setOnClickListener(button -> checkAnswer(2));

        // if you pressed button_4 - check it
        findViewById(R.id.button_4).setOnClickListener(button -> checkAnswer(3));

    }

    private void showQuitDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setTitle("Game over");
        if (!is_first_question_answered) {
            dialog.setMessage("You`ve decided to quit without choosing answer to 1st question, thus you didn`t win anything.");
        } else {
            int won_value = (current_question_num - 1) * 100000;
            dialog.setMessage("Congratulations, you have won $" + won_value + "!");
        }
        dialog.setPositiveButton("Ok", (dialogInterface, i) -> finish());
        dialog.create();
        dialog.show();
    }

    private void showLoseDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setTitle("Game over");
        dialog.setMessage("Unfortunately, you`ve chosen wrong answer and lost the game. Good luck in next game!");
        dialog.setPositiveButton("Ok", (dialogInterface, i) -> finish());
        dialog.create();
        dialog.show();
    }

    // setup of all UI components after clicking on 'start' in main menu
    private void startSetup() {
        setupCurrentQuestion();
        setupCheckBox();
        setupButtons();
        setupQuestionsNum();
        setupQuestionPrice();
    }

    // setup buttons as possible answers to the question
    private void setupButtons() {
        Button but1 = findViewById(R.id.button_1);
        Button but2 = findViewById(R.id.button_2);
        Button but3 = findViewById(R.id.button_3);
        Button but4 = findViewById(R.id.button_4);
        but1.setText(arrQuestions[current_question_num-1].variants_arr[0]);
        but2.setText(arrQuestions[current_question_num-1].variants_arr[1]);
        but3.setText(arrQuestions[current_question_num-1].variants_arr[2]);
        but4.setText(arrQuestions[current_question_num-1].variants_arr[3]);
        but1.setEnabled(true);
        but2.setEnabled(true);
        but3.setEnabled(true);
        but4.setEnabled(true);
    }

    // setup text of current question and shuffling possible answers
    private void setupCurrentQuestion() {
        TextView tvQuestionText = findViewById(R.id.text_of_question);
        tvQuestionText.setText(arrQuestions[current_question_num-1].text_of_question);
        List<String> list = Arrays.asList(arrQuestions[current_question_num-1].variants_arr);
        Collections.shuffle(list);
    }

    // setup price of question (in $)
    private void setupQuestionPrice() {
        TextView tvPrice  = findViewById(R.id.price);
        int question_price = (current_question_num - 1) * 100000;
        tvPrice.setText("$" + String.valueOf(question_price));
    }

    // setup current number of question and max number of questions
    private void setupQuestionsNum() {
        TextView tvQuestions = findViewById(R.id.question_number);
        tvQuestions.setText("Question " + String.valueOf(current_question_num) + " of " + options.number_of_questions);
    }

    // setup checkbox with a possibility to turn on one-time hint during the game
    private void setupCheckBox() {
        CheckBox cb = findViewById(R.id.is_hint);
        if (!options.is_hint_available || is_clicked_checkbox) {
            cb.setEnabled(false);
        } else {
            cb.setEnabled(true);
        }

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    is_clicked_checkbox = true;
                    cb.setEnabled(false);
                    int counter_incorrect_answers = 0;
                    while(true) {
                        Button but = findViewById(R.id.button_1);
                        if (!but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num - 1].answer)) {
                            Log.i("TAG: ", "answer=" + arrQuestions[current_question_num-1].answer);
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                        }
                        but = findViewById(R.id.button_2);
                        if (!but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num - 1].answer)) {
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                            if (counter_incorrect_answers >= 2) {
                                break;
                            }
                        }
                        but = findViewById(R.id.button_3);
                        if (!but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num - 1].answer)) {
                            but.setEnabled(false);
                            counter_incorrect_answers += 1;
                            if (counter_incorrect_answers >= 2) {
                                break;
                            }
                        }
                        but = findViewById(R.id.button_4);
                        if (!but.getText().toString().equalsIgnoreCase(arrQuestions[current_question_num - 1].answer)) {
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

    // initializing all questions, variants, answers, and shuffling of questions
    private void initializeQuestions() {
        arrQuestions[0] = new Questions("Where was the Internet invented?", new String[]{"United Kingdom", "China", "USA", "Soviet Union"}, "USA");
        arrQuestions[1] = new Questions("How many football teams take part in EURO competitions?", new String[]{"64", "32", "24", "16"}, "24");
        arrQuestions[2] = new Questions("Where is located the shortest metro path in the world?", new String[]{"Ukraine, Dnipro", "Germany, Aachen", "Italy, Milan", "Austria, Wien"}, "Ukraine, Dnipro");
        arrQuestions[3] = new Questions("The capital of Peru is ...", new String[]{"Cusco", "Piura", "Lima", "Tarapoto"}, "Lima");
        arrQuestions[4] = new Questions("The longest national anthem is in ... ", new String[]{"France", "Spain", "Sweden", "Greece"}, "Greece");
        arrQuestions[5] = new Questions("The Earth was formed ... billion years ago", new String[]{"14.9", "1.1", "4.3", "10.1"}, "4.3");
        arrQuestions[6] = new Questions("Harry Potter series consists of ... books", new String[]{"6", "7", "9", "5",},"7");
        arrQuestions[7] = new Questions("When Scorpions song 'Wind of Change' was written?", new String[]{"1990", "1997", "1984", "1993"}, "1990");
        arrQuestions[8] = new Questions("First F1 championship was held in ... ", new String[]{"1975", "1947", "1950", "1960"}, "1950");
        arrQuestions[9] = new Questions("The speed of sound equals", new String[]{"340 m/s", "500 m/s", "220 m/s", "600m/s"}, "340 m/s");
        List<Questions> list = Arrays.asList(arrQuestions);
        Collections.shuffle(list);
    }

    private void checkAnswer(int index) {
        is_first_question_answered = true;
        if (arrQuestions[current_question_num-1].variants_arr[index].equalsIgnoreCase(arrQuestions[current_question_num-1].answer) && current_question_num < options.number_of_questions) {
            current_question_num += 1;
            setupQuestionsNum();
            setupQuestionPrice();
            setupCurrentQuestion();
            setupButtons();
        } else if (current_question_num >= options.number_of_questions) {
            showQuitDialog();
        } else {
            showLoseDialog();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("OPTIONS", options);
        outState.putInt("CURRENT_QUESTION_NUM", current_question_num);
        outState.putBoolean("IS_FIRST_QUESTION_ANSWERED", is_first_question_answered);
        outState.putBoolean("IS_CLICKED_CHECKBOX", is_clicked_checkbox);
        outState.putParcelableArray("QUESTIONS", arrQuestions);
    }

}
