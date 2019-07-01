package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake> >
{  String url;
    public EarthquakeLoader(Context context,String url) {
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onForceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(url==null||url.isEmpty()){
            return null;
        }
        List<Earthquake> earthquakes=QueryUtils.extractEarthquakes(url);
        return earthquakes;
    }
}
