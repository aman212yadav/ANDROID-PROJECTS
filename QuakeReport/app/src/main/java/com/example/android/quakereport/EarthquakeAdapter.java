package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        String LOCATION_SEPARATOR=" of ",locationOffset,primaryLocation;
        Earthquake earthquake=getItem(position);
        TextView primaryLocationView=(TextView)listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetView=(TextView)listItemView.findViewById(R.id.location_offset);
        TextView magnitudeView=(TextView)listItemView.findViewById(R.id.magnitude);
        
        
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        
        
        
        magnitudeCircle.setColor(magnitudeColor);
        TextView date=(TextView)listItemView.findViewById(R.id.date);
        TextView time=(TextView)listItemView.findViewById(R.id.time) ;
        Date dateObject=new Date(earthquake.getTimeInMilliseconds());
        String originalLocation=earthquake.getPlace();
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String parts[]=originalLocation.split(LOCATION_SEPARATOR);
            locationOffset=parts[0]+LOCATION_SEPARATOR;
            primaryLocation=parts[1];
        }else{
            locationOffset=getContext().getString(R.string.near_the);
            primaryLocation=originalLocation;
        }
        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        magnitudeView.setText(formatDecimal(earthquake.getMagnitude()));
        date.setText(formatDate(dateObject));
        time.setText(formatTime(dateObject));
        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }

    private String formatDecimal(Double magnitude) {
        DecimalFormat format=new DecimalFormat("0.0");
        return format.format(magnitude);
    }

    private String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
