package com.example.gallerymulyani;

import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    int integer_position;
    private GridView gridView;
    GridViewAdapter adapter;
//    String[] arrayPhotos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridViewFolder);
//        integer_position = getIntent().getIntExtra("value", 0);

//        arrayPhotos =getIntent().getStringArrayExtra("Images");

        adapter = new GridViewAdapter(this,MainActivity.arrayListImages, integer_position);
        gridView.setAdapter(adapter);


    }
}
