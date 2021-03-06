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
import java.util.List;
import java.util.Set;

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeListItem> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<EarthquakeListItem> earthquakeListItems) {
        super(context, 0, earthquakeListItems);
    }
    private static final String LOCATION_SEPARATOR = " of ";
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView=convertView;
        if (ListItemView==null)
        {
            ListItemView=LayoutInflater.from(getContext()).inflate(R.layout.ethquake_list_item,parent,false);
        }

        EarthquakeListItem CurrentEarthquakeListItem =getItem(position);


        TextView magnitudeTextView = (TextView) ListItemView.findViewById(R.id.magnitude);
        TextView locationOffsetTV = (TextView) ListItemView.findViewById(R.id.locationOffset);
        TextView primaryLocationTV =(TextView)ListItemView.findViewById(R.id.primaryLocation);
        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject =new Date(CurrentEarthquakeListItem.getTimeInMilliseconds());
        TextView dateTextView = (TextView) ListItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate =formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        DecimalFormat formatter =new DecimalFormat("0.0");
        String magnitude =formatter.format(CurrentEarthquakeListItem.getMagnitude());
        // Find the TextView with view ID time
        TextView timeTextView = (TextView) ListItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);
        magnitudeTextView.setText(magnitude);
        //locationTextView.setText(CurrentEarthquakeListItem.getLocation());
        String Location=CurrentEarthquakeListItem.getLocation();
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(CurrentEarthquakeListItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



        // Check whether the Location string contains the " of " text
        if (Location.contains(LOCATION_SEPARATOR)) {
            // Split it.
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt"
            String[] parts= Location.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            String offset=parts[0]+LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            String primary=parts[1];
            locationOffsetTV.setText(offset);
            primaryLocationTV.setText(primary);
       } else    {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            String offset =getContext().getString(R.string.near_the);
            locationOffsetTV.setText(offset);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocationTV.setText(Location);


        }
        // Return the list item view that is now showing the appropriate data
        return ListItemView;



    }
    private int  getMagnitudeColor(double magnitude)
    {
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

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);

    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */ 

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat =new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


}
