package com.vlab.androidexample.adapters;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vlab.androidexample.R;

import java.util.List;

/**
 * Created by Vinh.Tran on 5/23/16.
 */
public class ActivityListAdapter extends ArrayAdapter<ActivityInfo> {

    List<ActivityInfo> itemList;

    public ActivityListAdapter(Context context, List<ActivityInfo> objects) {
        super(context, 0, objects);
        itemList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_activity, null);
            ViewHolder vh = new ViewHolder();
            vh.name = (TextView) view.findViewById(R.id.activity_name);
            view.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) view.getTag();
        ActivityInfo activityInfo = itemList.get(position);
        vh.name.setText(activityInfo.nonLocalizedLabel);

        return view;
    }

    static class ViewHolder {
        TextView name;
        TextView description;
    }
}
