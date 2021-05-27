package com.example.gallerymulyani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ImagesModel> arrayListImages = new ArrayList<>();
    ArrayList<String> arrayListPath = new ArrayList<>();

    boolean booleanFolder;
    FolderAdapter folderAdapter;
    GridView gridViewFolder;

//    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pop up permissions
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title);
        }

        gridViewFolder = (GridView) findViewById(R.id.gridViewFolder);
        gridViewFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                intent.putExtra("value", position);
                startActivity(intent);
            }
        });

    }

    public ArrayList<ImagesModel> FolderName_ImagePath(){
        arrayListImages.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;

        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()){
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < arrayListImages.size(); i++){
                if (arrayListImages.get(i).getStringFolder().equals(cursor.getString(column_index_folder_name))){
                    booleanFolder = true;
                    int_position = i;
                    break;
                }
                else {
                    booleanFolder = false;
                }
            }

            if (booleanFolder){
                arrayListPath.addAll(arrayListImages.get(int_position).getArrayList_ImagePath());
                arrayListPath.add(absolutePathOfImage);
                arrayListImages.get(int_position).setArrayList_ImagePath(arrayListPath);
            }
            else {
                arrayListPath.add(absolutePathOfImage);
                ImagesModel imagesModel = new ImagesModel();

                imagesModel.setStringFolder(cursor.getString(column_index_folder_name));
                imagesModel.setArrayList_ImagePath(arrayListPath);

                arrayListImages.add(imagesModel);
            }

        }

        for (int i = 0; i < arrayListImages.size(); i++){
            Log.e("FOLDER", arrayListImages.get(i).getStringFolder());

            for(int j = 0; j < arrayListImages.get(i).getArrayList_ImagePath().size(); j++){
                Log.e("FILE", arrayListImages.get(i).getArrayList_ImagePath().get(j));
            }
        }

        folderAdapter = new FolderAdapter(getApplicationContext(), arrayListImages);

        gridViewFolder.setAdapter(folderAdapter);

        return arrayListImages;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
