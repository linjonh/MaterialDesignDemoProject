package com.jaysen.mddp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[]{"title"},
                new int[]{android.R.id.text1}));

    }

    private List<? extends Map<String, ?>> getData() {
        ArrayList<HashMap<String, Object>> datas = new ArrayList<>();
        Intent sampleIntent = new Intent();
        sampleIntent.setAction(Intent.ACTION_MAIN);
        sampleIntent.addCategory("com.jaysen.example.MDDP");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(sampleIntent, 0);
        if (list == null) {
            return datas;
        }
        for (ResolveInfo resolveInfo : list) {
            CharSequence title = resolveInfo.loadLabel(pm);
            //filter label to get wanted activity class
            HashMap<String, Object> map = new HashMap<>();
            map.put("title", title.toString());
            map.put("intent", newActivityIntent(resolveInfo.activityInfo.packageName,
                    resolveInfo.activityInfo.name));
            datas.add(map);
        }
        return datas;
    }

    private Intent newActivityIntent(String pkg, String claz) {
        Intent intent = new Intent();
        intent.setClassName(pkg, claz);
        return null;
    }
    private Intent newClusterIntent(String pkg, String claz) {
        Intent intent = new Intent();
        intent.setClassName(pkg, claz);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
