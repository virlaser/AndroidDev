package edu.whut.liziyan.job2;

public class Song {
    private int coverId;
    private String songName;
    private String singer;
    private String songPath;

    public Song(int coverId, String songName, String singer, String songPath){
        this.coverId=coverId;
        this.singer=singer;
        this.songName=songName;
        this.songPath=songPath;
    }

    public int getCover(){return coverId;}
    public String getSongName(){return songName;}
    public String getSinger(){return singer;}
    public String getSongPath(){return songPath;}
}
