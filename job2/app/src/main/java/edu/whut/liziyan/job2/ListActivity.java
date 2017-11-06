package edu.whut.liziyan.job2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    /**为动态注册实例化一个自定义的广播接收器和一个IntentFilter**/
    private IntentFilter intentFilter;
    private HeadsetPlugReceiver headsetPlugReceiver;


    private List<Song> songArray = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        /**动态注册广播接收器
         * 耳机相关广播为android.intent.action.HEADSET_PLUG
         * 如何动态注册，
         * 具体参看“教学资源---示例代码---课件例子-- 广播的接收与发送--broadcasttest”**/
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
        /**调用initSong()之前需先动态获取权限Manifest.permission.READ_EXTERNAL_STORAGE
         * **/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
        } else {
            initSongs();
        }



        ListView songList=(ListView)findViewById(R.id.list_song);
        SongAdapter myAdapter=new SongAdapter(ListActivity.this,R.layout.song_item,songArray);
        songList.setAdapter(myAdapter);
        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songArray.get(position);
                /**修改点击显示歌曲文件路径**/
                Toast.makeText(ListActivity.this, song.getSongPath(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**在onDestroy()方法中通过调用unregisterReceiver()方法来取消广播接收器的注册**/
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(headsetPlugReceiver);
    }

    /**运行时权限申请的处理**/
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

    /**修改作业1中的init函数，通过ContentProvider获取本机音乐信息并填充歌曲数组
     * 歌手信息cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
     * 歌曲名称cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
     * 歌曲文件路径cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
     * 具体参考“教学资源---示例代码---课件例子-- ContentProvider获取本机联系人”**/
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

    /**创建一个广播接收器:
     * 新建一个类，让它继承自BroadcastReceiver，并重写父类的onReceive()方法
     * onReceive()方法内判断是插入还是拔出（可根据onreceiver的参数中的intent调用
     * getIntExtra方法获取键名为“state”的值，为1则表示耳机连接，为0表示耳机拔出）
     * ，并Toast相应信息
     * 如何创建广播接收器并处理收到的广播，
     * 具体参看“教学资源---示例代码---课件例子-- 广播的接收与发送--broadcasttest”**/
    class HeadsetPlugReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equalsIgnoreCase("android.intent.action.HEADSET_PLUG")){
                if(intent.hasExtra("state")){
                    if (intent.getIntExtra("state", 0) == 1){
                        Toast.makeText(context, "耳机已连接", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "耳机已断开", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

}
