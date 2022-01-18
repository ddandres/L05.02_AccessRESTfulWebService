/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

// Accesses a RESTful web service to get the current weather at the given city.
// The input parameter is a String in the format "city" or "city,country_code".
// The output parameter is a WeatherPOJO object containing the response from the web service.
public class WeatherHttpThread extends Thread {

    private final String url;
    private final WeakReference<RestfulWeatherActivity> reference;

    public WeatherHttpThread(RestfulWeatherActivity activity, String url) {
        reference = new WeakReference<>(activity);
        this.url = url;
    }

    @Override
    public void run() {
        try {
            // Creates a new URL from the URI
            final URL url = new URL(this.url);

            // Get a connection to the web service
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            // Process the response if it was successful (HTTP_OK = 200)
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Open an input channel to receive the response from the web service
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                // Create a Gson object through a GsonBuilder to process the response
                Gson gson = new Gson();
                // Deserializes the JSON response into a Quotation object
                final WeatherPOJO pojo = gson.fromJson(reader, WeatherPOJO.class);
                // Return the received WeatherPOJO object
                if (reference.get() != null) {
                    reference.get().runOnUiThread(() -> reference.get().displayWeather(pojo));
                }
                // Close the input channel
                reader.close();
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            if (reference.get() != null) {
                reference.get().runOnUiThread(() -> reference.get().displayWeather(null));
            }
        }

    }
}
