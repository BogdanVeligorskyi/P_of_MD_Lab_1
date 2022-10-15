package ua.cn.cpnu.pmp_lab_1.model;

public class Questions {
    public String text_of_question;
    public String var_1;
    public String var_2;
    public String var_3;
    public String var_4;
    public String answer;

    public Questions(String text_of_question, String var_1, String var_2, String var_3, String var_4, String answer) {
        this.text_of_question = text_of_question;
        this.var_1 = var_1;
        this.var_2 = var_2;
        this.var_3 = var_3;
        this.var_4 = var_4;
        this.answer = answer;
    }
}
