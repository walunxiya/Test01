package com.example.com.testdata.Help;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.com.testdata.R;
import com.example.com.testdata.bean.Comment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GXJ on 2016/4/22.
 */
public class HelpAdapter extends BaseAdapter{
    private Context context;
    private List<Comment> list = new ArrayList<>();

    public HelpAdapter(Context context, List<Comment> list) {
        super();
        this.context = context;
        this.list = list;
    }

    public void addComment(List<Comment> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_help,null);
            vh.iv_comment_image = (SimpleDraweeView)convertView.findViewById(R.id.iv_comment_image);
            vh.tv_comment_name = (TextView)convertView.findViewById(R.id.tv_comment_name);
            vh.tv_comment_time = (TextView)convertView.findViewById(R.id.tv_comment_time);
            vh.tv_comment_content = (TextView)convertView.findViewById(R.id.tv_comment_content);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.iv_comment_image.setBackgroundResource(list.get(position).getImage());
        vh.tv_comment_name.setText(list.get(position).getName());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");

        vh.tv_comment_time.setText(format.format(list.get(position).getDate()));
        vh.tv_comment_content.setText(list.get(position).getContent());
        return convertView;
    }

    class ViewHolder{
        SimpleDraweeView iv_comment_image;
        TextView tv_comment_name;
        TextView tv_comment_time;
        TextView tv_comment_content;
    }
}
