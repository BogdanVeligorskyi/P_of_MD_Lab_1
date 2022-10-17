package ua.cn.cpnu.pmp_lab_1.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

// class for saving options in app
public class Options implements Parcelable {

    // possible number of questions
    public static final List<String> QUESTIONS_NUM = Arrays.asList
            ("5", "6", "7", "8", "9", "10");

    // number of questions
    private int number_of_questions;

    // availability of hint during the game
    private boolean is_hint_available;

    // constructor
    public Options(int number_of_questions, boolean is_hint_available) {
        this.number_of_questions = number_of_questions;
        this.is_hint_available = is_hint_available;
    }

    // reading pre-saved options
    protected Options(Parcel in) {
        number_of_questions = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            is_hint_available = in.readBoolean();
        }
    }

    // implementing Parcelable interface
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

    // getter for number_of_questions field
    public int getNumber_of_questions() {
        return number_of_questions;
    }

    // getter for is_hint_available field
    public boolean getIs_hint_available() {
        return is_hint_available;
    }

    // setter for number_of_questions field
    public void setNumber_of_questions(int number_of_questions) {
        this.number_of_questions = number_of_questions;
    }

    // setter for is_hint_available field
    public void setIs_hint_available(boolean is_hint_available) {
        this.is_hint_available = is_hint_available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // saving options data
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.number_of_questions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(this.is_hint_available);
        }

    }
}
