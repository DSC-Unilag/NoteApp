package com.example.victo.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class NewNoteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.notelistsql.REPLY";

    private TextInputEditText mEditNoteView;
    private NoteAppDatabase mDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        mDb = NoteAppDatabase.getInstance(getApplicationContext());
        mEditNoteView = findViewById(R.id.edit_note_text);

        MaterialButton mbutton = findViewById(R.id.saveNote);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String note = mEditNoteView.getEditableText().toString();
                Date date = new Date();

                NoteEntry noteEntry = new NoteEntry(note, date);

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(note)){
                    setResult(RESULT_CANCELED, replyIntent);
                    Toast.makeText(getApplicationContext(), "empty not saved", Toast.LENGTH_LONG).show();
                }else {

                    mDb.noteDao().insert(noteEntry);
                    replyIntent.putExtra(EXTRA_REPLY, note);
                    setResult(RESULT_OK, replyIntent);
                    Toast.makeText(getApplicationContext(), "New Notes has being added to the list", Toast.LENGTH_LONG).show();
                    Log.d("NewActivity", "onClick: just added new text: " + note);
                }
                finish();
            }
        });
    }
}
