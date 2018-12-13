package com.example.victo.noteapp;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.ItemClickListener{

    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;

    private DrawerLayout mDrawerLayout;

     NoteViewModel noteViewModel;
    private RecyclerView mRecyclerview;

     NoteListAdapter adapter;
     NoteAppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        final FloatingActionButton fab =  findViewById(R.id.fab);

//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mRecyclerview = findViewById(R.id.recyclerView);

//final NoteListAdapter
         adapter = new NoteListAdapter(getApplicationContext());
        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteEntry>>() {
            @Override
            public void onChanged( List<NoteEntry> notes) {
                System.out.println("Main1 notes.size is "+notes.size());
                adapter.setNotes(notes);
            }
        });


//        mDrawerLayout = findViewById(R.id.drawer_layout);
//
//
//
//
//        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(@NonNull View view, float v) {
//
//                Log.d("MainActivity", "onDrawerSlide: drawer has just being slide");
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View view) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View view) {
//
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int i) {
//
//            }
//        });






        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        // set item as selected to persist highlight
//                        menuItem.setChecked(true);
//                        // close drawer when item is tapped
//                        mDrawerLayout.closeDrawers();
//
//                        // Add code here to update the UI based on the item selected
//                        // For example, swap UI fragments here
//
//                        return true;
//                    }
//                });

        fab.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "MainActivity";

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
                startActivity(intent);
                Log.d("MainActivity", "onClick: fab button has being clicked!");

            }
        });

        mDb = NoteAppDatabase.getInstance(getApplicationContext());

    }



  @Override
  public void onItemClickListener(int itemId){

  }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

