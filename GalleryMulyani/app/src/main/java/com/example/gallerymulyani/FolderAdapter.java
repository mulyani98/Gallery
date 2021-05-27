package com.example.gallerymulyani;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigInteger;
import java.util.ArrayList;

public class FolderAdapter extends ArrayAdapter<ImagesModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<ImagesModel>();

    private static class ViewHolder {
        TextView textView_FolderName, textView_FolderSize;
        ImageView imageViewPhoto;
    }

    public FolderAdapter(Context context, ArrayList<ImagesModel> arrayListMenu){
        super(context, R.layout.adapter_folder, arrayListMenu);
        this.context = context;
        this.arrayListImages = arrayListMenu;
    }

    @Override
    public int getCount() {
        Log.e("FOLDERADAPTER LIST SIZE", arrayListImages.size() +"");
        return arrayListImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (arrayListImages.size() > 0){
            return arrayListImages.size();
        }
        else
            return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        if (convertView == null){
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
//
//            viewHolder.textView_FolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
//            viewHolder.textView_FolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
//            viewHolder.imageViewPhoto = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
//
////            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position));
////            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);
//
//            convertView.setTag(viewHolder);
//
//        }
//        else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder = new ViewHolder();
//        convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

        View childView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);
        viewHolder.textView_FolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
        viewHolder.textView_FolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
        viewHolder.imageViewPhoto = (ImageView) convertView.findViewById(R.id.imageViewPhoto);

            Bitmap bitmap = BitmapFactory.decodeFile(arrayListImages.get(position));
            Bitmap bitmapThumbnail = Bitmap.createScaledBitmap(bitmap, 300, 300, false);

        convertView.setTag(viewHolder);

        viewHolder.textView_FolderName.setText(arrayListImages.get(position).getStringFolder());
        viewHolder.textView_FolderSize.setText(arrayListImages.get(position).getArrayList_ImagePath().size() + "");

        return childView;
//        return convertView;
    }
}
