package ua.cn.cpnu.pmp_lab_1.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Options implements Parcelable {
    public static final List<String> QUESTIONS_NUM = Arrays.asList
            ("5", "6", "7", "8", "9", "10");
    public int number_of_questions;
    public boolean is_hint_available;

    public Options(int number_of_questions, boolean is_hint_available) {
        this.number_of_questions = number_of_questions;
        this.is_hint_available = is_hint_available;
    }

    protected Options(Parcel in) {
        number_of_questions = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            is_hint_available = in.readBoolean();
        }
    }

    public static final Creator<Options> CREATOR = new Creator<Options>() {
        @Override
        public Options createFromParcel(Parcel in) {
            return new Options(in);
        }

        @Override
        public Options[] newArray(int size) {
            return new Options[size];
        }
    };

    public int getNumber_of_questions() {
        return number_of_questions;
    }

    public boolean getIs_hint_available() {
        return is_hint_available;
    }

   // static Options DEFAULT = Options (number_of_questions = 5, is_hint_available = true);

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.number_of_questions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.is_hint_available);
        }

    }
}





