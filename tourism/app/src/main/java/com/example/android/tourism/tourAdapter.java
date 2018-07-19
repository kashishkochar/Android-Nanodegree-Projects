package com.example.android.tourism;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kashish on 3/26/2018.
 */

public class tourAdapter extends ArrayAdapter<tour> {


    public tourAdapter(@NonNull Context context, ArrayList<tour> tourApp) {
        super(context, 0, tourApp);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item, parent, false);
        }

        tour currentPos = getItem(position);

        TextView textView1 = (TextView) listItemView.findViewById(R.id.item_text_view);
        textView1.setText(currentPos.getTextPart1());

        TextView textView2 = (TextView) listItemView.findViewById(R.id.description_text_view);
        textView2.setText(currentPos.getTextPart2());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentPos.hasImage()) {
            imageView.setImageResource(currentPos.getImageId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}