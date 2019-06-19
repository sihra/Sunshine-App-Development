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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //A TextView variable to store the weather display TextView
    TextView weatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        // Method findViewById retrieves the widgets in that UI that you need to interact with programmatically
        // Connect activity_forecast.xml to this java file
        weatherDisplay = findViewById(R.id.tv_weather_data);

        // String array of fake weather data and personal anecdotes
        String[] weatherData = {"Monday is gonna be a big one", "I hate Tuesdays", "Wednesdays are the best and the worst",
                "Thursdays are almost the best", "Friday's give me life", "Saturdays release me", "Sunday is bittersweet"};

        // Appends every weather data to text view to be displayed on app
        for(String weather:weatherData){
            weatherDisplay.append(weather + "\n\\n\n");
        }
    }
}