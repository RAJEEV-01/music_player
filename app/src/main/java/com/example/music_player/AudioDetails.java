package com.example.music_player;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AudioDetails implements Parcelable {
    private String name;
    private String path;

    public AudioDetails(String name, String path) {
        this.name = name;
        this.path = path;
    }

    protected AudioDetails(Parcel in) {
        name = in.readString();
        path = in.readString();
    }

    public static final Creator<AudioDetails> CREATOR = new Creator<AudioDetails>() {
        @Override
        public AudioDetails createFromParcel(Parcel in) {
            return new AudioDetails(in);
        }

        @Override
        public AudioDetails[] newArray(int size) {
            return new AudioDetails[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
    }
}
