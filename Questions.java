package ua.cn.cpnu.pmp_lab_1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Questions implements Parcelable {
    public String text_of_question;
    public String[] variants_arr;
    public String answer;

    public Questions(String text_of_question, String[] variants_arr, String answer) {
        this.text_of_question = text_of_question;
        this.variants_arr = variants_arr;
        this.answer = answer;
    }

    protected Questions(Parcel in) {
        text_of_question = in.readString();
        variants_arr = in.createStringArray();
        answer = in.readString();
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text_of_question);
        parcel.writeStringArray(variants_arr);
        parcel.writeString(answer);
    }
}
