package com.oc.rss.nihongo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;


import com.oc.rss.nihongo.entities.Radical;

import java.util.List;

public class RadicalAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> _list = Radical.all;

    public RadicalAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return _list.size();
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(80, 80));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(_list.get(position));
        textView.setHeight(20);
        textView.setWidth(20);
        textView.setTextSize(30);
        textView.setTextColor(Color.parseColor("#FEFEFE"));
        textView.setBackgroundColor(Color.parseColor("#0D50D8"));
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return textView;
    }


    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

}
