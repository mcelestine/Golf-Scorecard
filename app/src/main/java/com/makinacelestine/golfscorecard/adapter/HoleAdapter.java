package com.makinacelestine.golfscorecard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.makinacelestine.golfscorecard.R;
import com.makinacelestine.golfscorecard.model.Hole;

public class HoleAdapter extends BaseAdapter {

    private Context mContext;
    private Hole[] mHoles;
    private Hole mHole;

    public HoleAdapter(Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hole_list_item, null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.scoreTextView = (TextView) convertView.findViewById(R.id.scoreTextView);
            holder.subtractStrokeButton = (Button) convertView.findViewById(R.id.substractStrokeButton);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Hole hole = mHoles[position];

        holder.holeLabel.setText(hole.getHoleNumber());
        holder.scoreTextView.setText(Integer.toString(hole.getNumOfStrokes()));
        holder.subtractStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.scoreTextView.setText(subtractStroke(hole));
            }
        });

        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.scoreTextView.setText(addStroke(hole));
            }
        });

        return convertView;
    }

    // Returns String version of hole number after stroke (score) is added
    private String addStroke(Hole hole) {
        hole.addStroke();
        return Integer.toString(hole.getNumOfStrokes());
    }

    // Returns String version of stroke (score) after stroke (score) is subtracted
    private String subtractStroke(Hole hole) {
        hole.subtractStroke();
        return Integer.toString(hole.getNumOfStrokes());
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView scoreTextView;
        Button subtractStrokeButton;
        Button addStrokeButton;
    }
}
