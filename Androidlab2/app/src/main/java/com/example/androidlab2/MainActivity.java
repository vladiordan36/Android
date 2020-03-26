package com.example.androidlab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    ListView list ;
    TextView t, t_label;
    String itemValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        t = findViewById(R.id.texts1);
        t_label = findViewById(R.id.label_text);
        String[] values = new String[] { "Faina",
                "Malai",
                "Pateu",
                "Conserva"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String  itemValue    = (String) list.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Product no. "+ (position + 1) +"  Name : " +itemValue , Toast.LENGTH_SHORT)
                        .show();
                t.setText(itemValue);
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        setTextVisible(sharedPreferences.getBoolean("display_text", true));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
       if (id == R.id.action_settings) {
           Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
           startActivity(intent);
           return true;
       }else if (id == R.id.action_sensors) {
           Intent intent = new Intent(MainActivity.this, SensorsActivity.class);
           startActivity(intent);
           return true;
       }
       return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("display_text")) {
           setTextVisible(sharedPreferences.getBoolean("display_text",true));
        }
    }

    // Method to set Visibility of Text.
    private void setTextVisible(boolean display_text) {
        if (display_text) {
            t.setVisibility(View.VISIBLE);
            t_label.setVisibility(View.VISIBLE);
        } else {
            t.setVisibility(View.INVISIBLE);
            t_label.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (t != null) {
            itemValue = t.getText().toString();
        }
        savedInstanceState.putString("pos_index", itemValue);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        itemValue = savedInstanceState.getString("pos_index");
        if (t != null){
            t.setText(itemValue);
        }
    }
}
