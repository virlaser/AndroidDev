package edu.whut.liziyan.job1;

/**
 * Created by Lzy on 17/10/9.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class SongAdapter extends ArrayAdapter<Song> {
    private int resourceId;

    public SongAdapter(Context context, int textViewResourceId, List<Song> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.txt_song_name);
            viewHolder.author = (TextView) view.findViewById(R.id.txt_singer);
            viewHolder.image = (ImageView) view.findViewById(R.id.img_cover);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(song.getName());
        viewHolder.author.setText(song.getAuthor());
        viewHolder.image.setImageResource(song.getImage());
        return view;
    }

    class ViewHolder {
        ImageView image;
        TextView name;
        TextView author;
    }

}