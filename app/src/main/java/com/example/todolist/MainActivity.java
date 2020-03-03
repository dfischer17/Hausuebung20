package com.example.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<Task> taskList;
    private ListView listView;
    private TaskAdapter taskAdapter;
    private Button addButton;
    private static final int RQ_PREFERENCES = 1;
    private SharedPreferences prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        // listView initalisieren
        listView = findViewById(R.id.taskList);
        //todo task aus File einlesen
        taskAdapter = new TaskAdapter(this, R.layout.list_item, taskList);
        listView.setAdapter(taskAdapter);

        // add button initialisieren
        addButton = findViewById(R.id.addTaskButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View dialogView = getLayoutInflater().inflate(R.layout.add_dialog, null);
                new AlertDialog.Builder(MainActivity.this)
                        .setView(dialogView)
                        .setPositiveButton("Task hinzufuegen", (dialog, which) -> handleAddDialog(dialogView))
                        .setNegativeButton("Abbrechen", null)
                        .show();

            }
        });

        // Preference initialisieren
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        preferenceChangeListener = (sharedPrefs, key) -> preferenceChanged(sharedPrefs, key);

        linearLayout = findViewById(R.id.background);
    }

    private void handleAddDialog(final View vDialog) {
        DatePicker datePicker = vDialog.findViewById(R.id.datePicker);
        TimePicker timePicker = vDialog.findViewById(R.id.timePicker);
        EditText nameView = vDialog.findViewById(R.id.taskName);

        // Datum und Zeit auslesen
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        LocalDateTime timestamp = LocalDateTime.of(year, month, day, hour, minute);

        // Name auslesen
        String name = nameView.getText().toString();

        addTask(new Task(name, timestamp));
    }

    private void addTask(Task task) {
        taskList.add(task);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuPreferences) {
            Intent intent = new Intent(this, MySettingsActivity.class);
            startActivityForResult(intent, RQ_PREFERENCES);
        }
        return super.onOptionsItemSelected(item);
    }

    private void preferenceChanged(SharedPreferences sharedPrefs, String key) {
        String sValue = sharedPrefs.getString(key, "");

        // Entsprechende Preference akutalisieren
        switch (key) {
            case "hideCompletedTasks":
                break;

            case "selectedColorCode":
                int color = Color.parseColor(prefs.getString("Grau", ""));
                linearLayout.setBackgroundColor(color);
                break;
        }

        /*
        Map<String, ?> allEntries = sharedPrefs.getAll();
        String sValue = "";
        if (allEntries.get(key) instanceof Boolean)
            sValue = sharedPrefs.getString(key,"");
        else if (allEntries.get(key) instanceof )
        */
    }
}
