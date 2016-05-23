package com.vlab.androidexample.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.vlab.androidexample.R;
import com.vlab.androidexample.adapters.ActivityListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlab.smartapps@gmail.com on 4/13/16.
 * this class just for demo example
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listActivities = (ListView) findViewById(R.id.list_activities);

        final List<ActivityInfo> activityList = getActivityList();
        listActivities.setAdapter(new ActivityListAdapter(this, activityList));

        listActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActivityInfo item = activityList.get(i);

                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, item.name);
                startActivity(intent);
            }
        });
    }

    private List<ActivityInfo> getActivityList() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = this.getPackageManager();

        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ApplicationInfo applicationInfo = info.applicationInfo;

        List<ActivityInfo> filterList = new ArrayList<>();
        ActivityInfo[] allList = info.activities;
        for (ActivityInfo activityInfo : allList) {
            if(!TextUtils.isEmpty(activityInfo.nonLocalizedLabel) && activityInfo.name.startsWith(getPackageName())){
                filterList.add(activityInfo);
            }
        }

        return  filterList;
    }
}
