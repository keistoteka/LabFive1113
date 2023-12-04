package com.example.labfive1113;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DataLoader extends AsyncTask<Void, Void, List<String>> {
    @SuppressLint("StaticFieldLeak")
    private final MainActivity mainActivity;
    private final Parser parser;

    public DataLoader(MainActivity mainActivity, Parser parser) {
        this.mainActivity = mainActivity;
        this.parser = parser;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        try {
            URL url = new URL("http://www.floatrates.com/daily/usd.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            return parser.parseData(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> data) {
        if (data != null) {
            // Update UI in MainActivity
            mainActivity.updateListView(data);
        } else {
            // Handle the case where data is null (e.g., network error)
            // You may want to show an error message or take appropriate action.
        }
    }
}
