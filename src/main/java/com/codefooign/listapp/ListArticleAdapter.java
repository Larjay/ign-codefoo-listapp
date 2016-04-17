package com.codefooign.listapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListArticleAdapter extends ArrayAdapter<ListArticle> {

    Context context;
    int layoutResId;
    ListArticle data[] = null;

    public ListArticleAdapter(Context context, int layoutResId, ListArticle[] data) {
        super(context, layoutResId, data);
        this.layoutResId = layoutResId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResId, parent, false);

            holder = new ViewHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtTimeSince = (TextView) row.findViewById(R.id.txtTimeSince);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        ListArticle list = data[position];

        holder.imgIcon.setImageBitmap(list.thumbnail);
        holder.txtTitle.setText(list.title);
        holder.txtTimeSince.setText(String.valueOf(list.timePosted));

        return row;
    }


    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtTimeSince;
    }




}
