package ru.startandroid.lesson51;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "myLogs";
    Button btn1;
    private static final int CM_DELETE_ID = 1;
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "sometext " + i);
            m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
            data.add(m);
        }
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE};

        int[] to = {R.id.tvText, R.id.ivImg};

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        data.clear();
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);


    }

//        public void onButtonClick(View v) {
//        m = new HashMap<String, Object>();
//        m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
//        m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
//        data.add(m);
//        sAdapter.notifyDataSetChanged();



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn:
                Log.d(TAG,"OnClick Button Create");
                m = new HashMap<String, Object>();
                m.put(ATTRIBUTE_NAME_TEXT, "sometext " + (data.size() + 1));
                m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.ic_launcher_foreground);
                data.add(m);
                sAdapter.notifyDataSetChanged();
                break;
            case R.id.btn1:
                Log.d(TAG, "OnClick Button Clear");
                data.clear();
                sAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Udalit zapis");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}

