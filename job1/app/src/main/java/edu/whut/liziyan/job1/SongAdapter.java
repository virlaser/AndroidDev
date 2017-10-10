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

/**
 * 自定义的适配器，这个适配器继承自ArrayAdapter，并将泛型指定为Song类
 */

public class SongAdapter extends ArrayAdapter<Song> {
    private int resourceId;

    public SongAdapter(Context context, int textViewResourceId, List<Song> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**需参考教学资源中课件例子中的“ LIstView的使用”中的FruitAdapter
         * 重写适配器的getView方法，
         * 注意使用convertView与ViewHolder提高效率
         * **/
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
        /**需参考教学资源中课件例子中的“ LIstView的使用”中的FruitAdapter，
         * 定义合适的ViewHolder**/
        ImageView image;
        TextView name;
        TextView author;
    }

}