package com.example.notes_app;

//app for creating and storing notes

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    //ArrayList and Adapter to link to the ListView
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    //creating a menu to add new notes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //using MenuInflater to link the menu to add_new_note_menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        //creating intent to jump to new activity to create a new note
        if(item.getItemId() == R.id.add_note) {

            Intent intent = new Intent(getApplicationContext(), EditNote.class);

            startActivity(intent);

            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listview to display notes
        ListView listView = (ListView) findViewById(R.id.listView);

        //checking SharedPrefences to display contents from the HashSet
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes_app", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null) {

            notes.add("This is a note");
        } else {

            notes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        listView.setAdapter(arrayAdapter);

        //setting an OnItemClickListener to edit a note when it is clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            //making an intent that will edit the note when clicked
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), EditNote.class);
                intent.putExtra("noteID", i);
                startActivity(intent);

            }
        });

        //adding an OnItemLongClickListener to allow deletion of notes
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int deleteItem = i;

                //creating an alert to ask user if they want to delete the selected note
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            //if Yes is pressed then delete the note from notes
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                //removes the item and updates arrayAdapter
                                notes.remove(deleteItem);
                                arrayAdapter.notifyDataSetChanged();

                                //using SharedPreferences for permanent storage, updating when a note is deleted
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes_app", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet<>(MainActivity.notes);

                                sharedPreferences.edit().putStringSet("notes", set).apply();

                            }
                        })
                        //no action when No is clicked
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });





    }
}