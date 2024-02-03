package com.example.schedulerapp;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.classschedule_test.databinding.ActivityInfoClassBinding;

import io.realm.Realm;

public class InfoClassActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_class);

        EditText course = findViewById(R.id.course);
        EditText professor = findViewById(R.id.professor);
        EditText note = findViewById(R.id.note);
        EditText time = findViewById(R.id.time);
        MaterialButton saveButton = findViewById(R.id.saveButton);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseType = course.getText().toString();
                String prof = professor.getText().toString();
                String noteClass = note.getText().toString();
                String date = time.getText().toString();

                realm.beginTransaction();
                classInfo myClass = realm.createObject(classInfo.class);
                myClass.setTittle(courseType);
                myClass.setDescription(prof);
                myClass.setNote(noteClass);
                myClass.setTime(date);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
