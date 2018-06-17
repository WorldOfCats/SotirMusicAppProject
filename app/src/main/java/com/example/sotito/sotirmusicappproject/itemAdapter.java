package com.example.sotito.sotirmusicappproject;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class itemAdapter extends ArrayAdapter<item> {
    private int styleView;

    public itemAdapter(Context context, List<item> entityList, int colorPage) {
        super(context, 0, entityList);
        this.styleView = colorPage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listElement = convertView;
        if (listElement == null) {
            listElement = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_entity, parent, false);
        }
        item dataShard = getItem(position);

        ImageView pictureSong = listElement.findViewById(R.id.test_songs);
        if (dataShard.hasImage()) {
            pictureSong.setImageResource(dataShard.getmImageResourceId());
            pictureSong.setVisibility(View.VISIBLE);
        } else {
            pictureSong.setVisibility(View.GONE);
        }

        TextView nameSongTextView = listElement.findViewById(R.id.test_songs);
        nameSongTextView.setText(dataShard.getNameInstrument());

        View textItem = listElement.findViewById(R.id.test_songs);
        int style = ContextCompat.getColor(getContext(), styleView);
        textItem.setBackgroundColor(style);

        return listElement;

    }
}
