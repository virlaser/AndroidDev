package edu.whut.liziyan.job1;

/**
 * Created by Lzy on 17/10/9.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    //定义一个歌曲数组，作为ListView的数据源
    private List<Song> songArray = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /******1.实例化1个TextView对象和1个Button对象，
         并通过findViewById方法实现layout中的控件与java对象的关联*****/
        TextView text = (TextView) findViewById(R.id.txt_welcome);
        Button btn = (Button) findViewById(R.id.btn_back);

        /******2.使用getIntent方法获取Intent对象，并使用Intent对象的getStringExtra方法
         * 获取传过来的用户名，再通过字符串连接运算（+）形成需要的字符串，
         * 调用TextView的setText方法放到TextView上显示*****/
        Intent intent = getIntent();
        String data = intent.getStringExtra("usr");
        String str = "欢迎您," + data;
        text.setText(str);

        /******3.为Button添加监听器，并在其OnClick方法中实现向登录Activity的跳转
         * 实例化一个Intent对象
         * 通过startActivity方法实现Activity的切换。
         * *****/
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        /******3.调用initSongs函数初始化歌曲数组
         * 实例化SongAdapter对象
         *实例化1个ListView对象,并通过findViewById方法实现layout中的控件与java对象的关联
         * 调用ListView对象的setAdapter方法，给ListView绑定适配器
         * 为ListView的Item绑定点击时间，并实现点击后Toast歌曲名称。
         * 可参考教学资源中课件例子中的“ LIstView的使用”中的MainActivity中的写法
         * *****/
        initSongs();
        SongAdapter adapter = new SongAdapter(ListActivity.this, R.layout.song_item, songArray);
        ListView listView = (ListView) findViewById(R.id.list_song);
        listView.setAdapter(adapter);

    }

    private void initSongs(){
        /******定义initSongs函数，初始化歌曲数组
         * 可参考教学资源中课件例子中的“ LIstView的使用”中的MainActivity中initruit的写法
         * *****/
        Song chengdu = new Song("成都", "赵雷", R.drawable.cd);
        songArray.add(chengdu);
        Song lilian = new Song("莉莉安", "宋冬野", R.drawable.lla);
        songArray.add(lilian);
        Song nanshannan = new Song("南山南", "马頔", R.drawable.nsn);
        songArray.add(nanshannan);
        Song qimiao = new Song("奇妙能力歌", "陈粒", R.drawable.qmnlg);
        songArray.add(qimiao);
    }
}