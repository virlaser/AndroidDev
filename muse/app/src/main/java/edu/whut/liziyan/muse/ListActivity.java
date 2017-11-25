package edu.whut.liziyan.muse;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private HeadsetPlugReceiver headsetPlugReceiver;

    private List<Song> songArray = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        headsetPlugReceiver = new HeadsetPlugReceiver();
        registerReceiver(headsetPlugReceiver, intentFilter);


        TextView welcomeTxt=(TextView)findViewById(R.id.txt_welcome);
        Button backBtn=(Button)findViewById(R.id.btn_back);
        String uname=this.getIntent().getStringExtra("uname");
        welcomeTxt.setText("欢迎您，"+uname+"!");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
        } else {
            initSongs();
        }

        final ListView songList=(ListView)findViewById(R.id.list_song);
        SongAdapter myAdapter=new SongAdapter(ListActivity.this,R.layout.song_item,songArray);
        songList.setAdapter(myAdapter);
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songArray.get(position);
                Intent intent = new Intent(ListActivity.this, PlayerActivity.class);
                //当前歌曲
                intent.putExtra("song", song);
                //当前歌曲坐标
                intent.putExtra("position", position);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("songs", (ArrayList<Song>)songArray);
                //歌曲数组
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(headsetPlugReceiver);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initSongs();
                } else {
                    Toast.makeText(this, "未授权，功能无法实现", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void initSongs() {
        Cursor cursor = null;
        cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 获取专辑图片
                // 获取歌手信息
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //获取歌曲名称
                String disName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //获取文件路径
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                Song song = new Song(R.drawable.default_cover, disName, artist, url);
                songArray.add(song);
            }
        }
    }

    class HeadsetPlugReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("android.intent.action.HEADSET_PLUG")) {
                if (intent.hasExtra("state")) {
                    //获取NotificationManager对象
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (intent.getIntExtra("state", 0) == 1) {
                        //发送耳机链接通知
                        Notification notification = new NotificationCompat.Builder(context)
                                .setContentTitle("耳机状态变化")
                                .setContentText("耳机已连接")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/game.ogg")))
                                .setVibrate(new long[]{0, 1000, 1000, 1000})
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .setAutoCancel(true)
                                .build();
                        manager.notify(1, notification);
                    } else {
                        //发送耳机断开通知
                        Notification notification = new NotificationCompat.Builder(context)
                                .setContentTitle("耳机状态变化")
                                .setContentText("耳机已断开")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/game.ogg")))
                                .setVibrate(new long[]{0, 1000, 1000, 1000})
                                .setPriority(NotificationCompat.PRIORITY_MAX)
                                .setAutoCancel(true)
                                .build();
                        manager.notify(1, notification);
                    }
                }
            }
        }
    }
}
