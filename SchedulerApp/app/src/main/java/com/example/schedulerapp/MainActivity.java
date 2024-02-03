package com.example.schedulerapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.classschedule_test.classInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classschedule_test.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton add = findViewById(R.id.infoButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, classInfo.class));
            }
        });
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<classInfo> noteList = realm.where(classInfo.class).sort("createTime", Sort.DESCENDING).findAll();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Apdater myAdapter = new Apdater(getApplicationContext(), noteList);
        recyclerView.setAdapter(myAdapter);
        noteList.addChangeListener(new RealmChangeListener<RealmResults<classInfo>>() {
            @Override
            public void onChange(RealmResults<classInfo> classInfos) {
                myAdapter.notifyDataSetChanged();
            }
        });
    }
}
