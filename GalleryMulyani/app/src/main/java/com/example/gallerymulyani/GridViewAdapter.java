package com.example.gallerymulyani;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<ImagesModel> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ImagesModel> arrayListImages = new ArrayList<>();
    int int_position;


    private static class ViewHolder{
        TextView textViewFolderName, textViewFolderSize;
        ImageView imageViewImages;
    }

    public GridViewAdapter(Context context, ArrayList<ImagesModel> arrayListImages, int int_position){
        super(context, R.layout.adapter_folder, arrayListImages);
        this.context = context;
        this.arrayListImages = arrayListImages;
        this.int_position = int_position;
    }

    @Override
    public int getCount() {
        Log.e("ADAPTER LIST SIZE", arrayListImages.get(int_position).getArrayList_ImagePath().size() + "");
        return arrayListImages.get(int_position).getArrayList_ImagePath().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (arrayListImages.get(int_position).getArrayList_ImagePath().size() > 0){
            return arrayListImages.get(int_position).getArrayList_ImagePath().size();
        }
        else {
            return 1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_folder, parent, false);

            viewHolder.textViewFolderName = (TextView) convertView.findViewById(R.id.textViewFolderName);
            viewHolder.textViewFolderSize = (TextView) convertView.findViewById(R.id.textViewFolderSize);
            viewHolder.imageViewImages = (ImageView) convertView.findViewById(R.id.imageViewPhoto);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewFolderName.setVisibility(View.GONE);
        viewHolder.textViewFolderSize.setVisibility(View.GONE);

        return  convertView;
    }
}
