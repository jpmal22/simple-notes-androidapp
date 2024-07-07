package com.example.notes_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

//activity to enable editing notes
public class EditNote extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //creating an EditText variable using findViewById
        EditText editText = (EditText) findViewById(R.id.editTextTextMultiLine);

        //getting the noteID passed to this activity from MainActivity by creating an intent
        Intent intent = getIntent();
        //setting the defaultValue of -1 to prevent an invalid noteID being passed as listView ID start at 0
        noteID = intent.getIntExtra("noteID", -1);

        //if value is valid then get value of notes text and display in the editText
        //else creates a new note
        if (noteID != -1) {

            editText.setText(MainActivity.notes.get(noteID));
        } else {

            MainActivity.notes.add("");
            noteID = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }

        //saves the note as it is edited by the user
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //updating notes ArrayList from MainActivity
                MainActivity.notes.set(noteID, String.valueOf(charSequence));
                //updating the listView view via the arrayAdapter.
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //using SharedPrefences to store notes into permanent storage, updating when a note is edited
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes_app", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(MainActivity.notes);

                sharedPreferences.edit().putStringSet("notes", set).apply();



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}