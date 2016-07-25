package com.makinacelestine.golfscorecard.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.makinacelestine.golfscorecard.R;
import com.makinacelestine.golfscorecard.adapter.HoleAdapter;
import com.makinacelestine.golfscorecard.model.Hole;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_FILE = "com.makinacelestine.golfscorecard.preferences";
    private static String KEY_SCORE = "key_score";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private Hole[] mHoles = new Hole[18];
    private HoleAdapter mHoleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        int strokes = 0;

        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(KEY_SCORE + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        ListView holesListView = (ListView) findViewById(R.id.holesListView);
        mHoleListAdapter = new HoleAdapter(this, mHoles);
        holesListView.setAdapter(mHoleListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (int i = 0; i < mHoles.length; i++) {
            // 18 different key-value pairs for each hole
            mEditor.putInt(KEY_SCORE + i, mHoles[i].getNumOfStrokes());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_strokes:
                clearStrokes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearStrokes() {
        // clear sharedPrefs file
        mEditor.clear();
        mEditor.apply();

        // clear data model
        for (Hole hole : mHoles) {
            hole.setNumOfStrokes(0);
        }
        mHoleListAdapter.notifyDataSetChanged();
    }
}
