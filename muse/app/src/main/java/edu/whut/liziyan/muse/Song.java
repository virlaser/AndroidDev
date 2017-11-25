package edu.whut.liziyan.muse;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable{
    private int coverId;
    private String songName;
    private String singer;
    private String songPath;

    public int getCoverId() {
        return coverId;
    }

    public void setCoverId(int coverId) {
        this.coverId = coverId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public Song(int coverId, String songName, String singer, String songPath){
        super();
        this.coverId=coverId;
        this.singer=singer;
        this.songName=songName;
        this.songPath=songPath;
    }

    public Song(){
        super();
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(coverId);
        parcel.writeString(songName);
        parcel.writeString(singer);
        parcel.writeString(songPath);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>(){
        @Override
        public Song createFromParcel(Parcel parcel) {
            Song song = new Song();
            song.setCoverId(parcel.readInt());
            song.setSongName(parcel.readString());
            song.setSinger(parcel.readString());
            song.setSongPath(parcel.readString());
            return song;
        }

        @Override
        public Song[] newArray(int i) {
            return new Song[i];
        }
    };
}
