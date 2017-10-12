package edu.whut.liziyan.job1;

/**
 * Created by Lzy on 17/10/9.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Song> songArray = new ArrayList<Song>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        TextView text = (TextView) findViewById(R.id.txt_welcome);
        Button btn = (Button) findViewById(R.id.btn_back);

        Intent intent = getIntent();
        String data = intent.getStringExtra("usr");
        String str = "欢迎您," + data;
        text.setText(str);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        initSongs();
        SongAdapter adapter = new SongAdapter(ListActivity.this, R.layout.song_item, songArray);
        ListView listView = (ListView) findViewById(R.id.list_song);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Song song = songArray.get(i);
                Toast.makeText(ListActivity.this, song.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSongs(){
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