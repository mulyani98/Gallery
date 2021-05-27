package com.example.gallerymulyani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ImagesModel> arrayListImages = new ArrayList<>();

    boolean booleanFolder;
    FolderAdapter folderAdapter;
    GridView gridViewFolder;
    private static final int REQUEST_PERMISSIONS = 100;

//    //trial and error
//    public static ArrayList<ImagesModel> pictureFolder = new ArrayList<>();
//    ArrayList<String> picturePath = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            ActionBar backButton = getSupportActionBar();
            backButton.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.title);
        }

        //second
        gridViewFolder = (GridView) findViewById(R.id.gridViewFolder);
        gridViewFolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                intent.putExtra("value", i);
                startActivity(intent);
            }
        });

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }
//        else {
//            Log.e("Else","Else");
//            folderName_ImagePath();
//        }
        folderName_ImagePath();

        //trial and error

        //pop up permissions
//        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
//
//            } else {
//                ActivityCompat.requestPermissions(MainActivity.this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
//            }
//        }

//        RecyclerView recyclerView =findViewById(R.id.recyclerFolder);
//        recyclerView.hasFixedSize();
//        ArrayList<ImagesModel> folds = getPicturePath();
////        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//
//        if (folds.isEmpty()){
//            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
//        }
//        else{
//            RecyclerView.Adapter folderAdapter = new FolderAdapter(MainActivity.this, folds);
//            recyclerView.setAdapter(folderAdapter);
//        }

        //original
//        ArrayList<ImagesModel> folds = folderName_ImagePath();
//        FolderAdapter folderAdapter = new FolderAdapter(MainActivity.this, folds);

//        if (folds.isEmpty()){
//            Toast.makeText(MainActivity.this, "There is no Album here",Toast.LENGTH_LONG).show();
//        }
//        else{
//            gridViewFolder.setAdapter(folderAdapter);
//        }

    }

    //original
    public ArrayList<ImagesModel> folderName_ImagePath(){
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
                ArrayList<String> arrayListPath = new ArrayList<>(arrayListImages.get(int_position).getArrayList_ImagePath());
                arrayListPath.add(absolutePathOfImage);
                arrayListImages.get(int_position).setArrayList_ImagePath(arrayListPath);
            }
            else {
                ArrayList<String> arrayListPath = new ArrayList<>();
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

        FolderAdapter folderAdapter = new FolderAdapter(getApplicationContext(), arrayListImages);
        gridViewFolder.setAdapter(folderAdapter);
        return arrayListImages;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        folderName_ImagePath();
                    } else {
                        Toast.makeText(MainActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //trial and error
//    ArrayList<ImagesModel> getPicturePath(){
//
//
//        Uri allImagesUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
//
//        Cursor cursor = this.getContentResolver().query(allImagesUri, projection, null, null, null);
//
//
//        try{
//            if (cursor != null){
//                cursor.moveToFirst();
//            }
//            do {
//                ImagesModel folder = new ImagesModel();
//                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
//                String folders = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
//                String dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//
//
//                String folderPaths = dataPath.substring(0, dataPath.lastIndexOf(folders + "/"));
//                folderPaths = folderPaths+folders+"/";
//
//                if (!picturePath.contains(folderPaths)){
//                    picturePath.add(folderPaths);
//
//                    folder.setPath(folderPaths);
//                    folder.setFolderName(folders);
//                    folder.setFirstPic(dataPath);
//                    folder.addPics();
//                    pictureFolder.add(folder);
//                }
//                else{
//                    for (int i = 0; i < pictureFolder.size(); i++){
//                        if(pictureFolder.get(i).getPath().equals(folderPaths)){
//                            pictureFolder.get(i).setFirstPic(dataPath);
//                            pictureFolder.get(i).addPics();
//                        }
//                    }
//                }
//            }
//            while (cursor.moveToNext());
//            cursor.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i<pictureFolder.size(); i++){
//            Log.d("Picture Folder", pictureFolder.get(i).getFolderName()+ " and path = "+pictureFolder.get(i).getPath()+" "+pictureFolder.get(i).getNumberOfPics());
//
//        }
//
//        return pictureFolder;
//    }
}

