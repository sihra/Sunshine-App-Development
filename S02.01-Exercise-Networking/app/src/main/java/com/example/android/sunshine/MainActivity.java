/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // Performs network request to get data from website and display on screen
        loadWeatherData();
    }

    private void loadWeatherData(){
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherAsyncTask().execute(location);
    }

    /**
     * Class that gets the weather based off the user's location
     */
    public class FetchWeatherAsyncTask extends AsyncTask<String, Void, String[] >{

        /**
         * Method that carries out getting the weather
         */
        protected String[] doInBackground(String... params) {
            // If the user doesn't have a zip code, there's nothing to return
            if(params.length == 0){
                return null;
            }
            String location = params[0];
            URL compileWeather = NetworkUtils.buildUrl(location);

            try{
                String jsonPullWeather = NetworkUtils.getResponseFromHttpUrl(compileWeather);

                String[] jsonWeatherData = OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, jsonPullWeather);

                return jsonWeatherData;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }

        /**
         * Method that displays the results of the network requests onto the screen to showcase weather data from the user's location
         * @param s : compilation of data
         */
        protected void onPostExecute(String[] s) {
            if (s != null) {
                for (String weatherString : s) {
                    mWeatherTextView.append((weatherString) + "\n\n\n");
                }
            }
        }
    }
}