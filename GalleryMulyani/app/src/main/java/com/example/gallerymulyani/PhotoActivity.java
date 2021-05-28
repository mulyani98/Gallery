package com.example.gallerymulyani;

import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    int position;
    private GridView gridView;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.album);
        }


        gridView = (GridView) findViewById(R.id.gridViewFolder);
//        position = getIntent().getIntExtra("folderName", 0);
        position=getIntent().getIntExtra("value",0);

        adapter = new GridViewAdapter(this,MainActivity.arrayListImages, position);
        gridView.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
