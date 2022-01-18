/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Gets the current weather at a given city from a RESTful web service.
// Web service Documentation: http://openweathermap.org/api
public class RestfulWeatherActivity extends AppCompatActivity {

    // Hold references to View objects
    EditText etCity;
    ProgressBar pbConnecting;
    TextView tvTemperature;
    TextView tvDescription;
    ImageView ivIcon;
    Button bCheckWeather;
    Spinner spinner;

    // Request queue for Volley
    RequestQueue queue;

    WeatherRetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful_weather);

        // Keep a reference to all View objects
        etCity = findViewById(R.id.etWeather);
        pbConnecting = findViewById(R.id.pbConnecting);
        tvTemperature = findViewById(R.id.tvWeatherTemperature);
        tvDescription = findViewById(R.id.tvWeatherDescription);
        ivIcon = findViewById(R.id.ivWeatherIcon);
        bCheckWeather = findViewById(R.id.bWeather);
        spinner = findViewById(R.id.spinner);

        bCheckWeather.setOnClickListener(v -> getWeather());

        // Create request queue for Volley
        queue = Volley.newRequestQueue(getApplicationContext());

        // Create the interface for Retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(WeatherRetrofitInterface.class);

    }

    // Handles the event to get the weather.
    private void getWeather() {

        // Hide the soft keyboard
        final InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        manager.hideSoftInputFromWindow(
                bCheckWeather.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        // Check that something has been entered as city name
        if (!etCity.getText().toString().isEmpty()) {
            // Check that network connectivity exists
            if (isConnected()) {

                // Displays an indeterminate ProgressBar to show that an operation is in progress
                pbConnecting.setVisibility(View.VISIBLE);
                // Disable the button so the user cannot launch multiple requests
                bCheckWeather.setEnabled(false);

                final String selectedMethod = spinner.getSelectedItem().toString();
                // Use Retrofit to access the web service
                if (selectedMethod.equals(getString(R.string.retrofit))) {
                    // retrofit call, including
                    // listener for successful requests, and
                    // listener for any errors
                    final Call<WeatherPOJO> call =
                            retrofitInterface.getCurrentWeather(etCity.getText().toString());
                    call.enqueue(new Callback<WeatherPOJO>() {
                        @Override
                        public void onResponse(@NonNull Call<WeatherPOJO> call, @NonNull Response<WeatherPOJO> response) {
                            displayWeather(response.body());
                        }

                        @Override
                        public void onFailure(@NonNull Call<WeatherPOJO> call, @NonNull Throwable t) {
                            displayWeather(null);
                        }
                    });
                } else {
                    // Build the URI to the RESTful web services
                    Uri.Builder builder = new Uri.Builder();
                    builder.scheme("https");
                    builder.authority("api.openweathermap.org");
                    builder.appendPath("data");
                    builder.appendPath("2.5");
                    builder.appendPath("weather");
                    // As being a GET request, include the parameters on the URI
                    builder.appendQueryParameter("APPID", "879247f3f3bda41f02246d88e5b6deaa");
                    builder.appendQueryParameter("mode", "json");
                    builder.appendQueryParameter("units", "metric");
                    builder.appendQueryParameter("q", etCity.getText().toString());


                    // Use Volley to access the web service
                    if (selectedMethod.equals(getString(R.string.volley))) {
                        // Custom Volley request, including URl,
                        // listener for successful requests, and
                        // listener for any errors
                        WeatherVolleyRequest request = new WeatherVolleyRequest(builder.build().toString(),
                                // Display the retrieved data
                                this::displayWeather,
                                // Notify the user that the request could not be completed,
                                // probably the name of the city was wrong
                                error -> displayWeather(null));
                        // Add the request to the queue for processing
                        queue.add(request);

                    } else { // Use HttsUrlConnection to access the web service
                        // Launch the Thread in charge of accessing the web service
                        new WeatherHttpThread(RestfulWeatherActivity.this, builder.build().toString()).start();
                    }
                }

            }
            // Notify the user that the device has not got Internet connection
            else {
                Toast.makeText(
                        this,
                        getResources().getString(R.string.not_connected),
                        Toast.LENGTH_SHORT).show();
            }
        }
        // Notify the user that it is required to enter a city name
        else {
            Toast.makeText(
                    this,
                    getResources().getString(R.string.not_data),
                    Toast.LENGTH_SHORT).show();
        }
    }

    // Determines whether the device has got Internet connection.
    public boolean isConnected() {
        boolean result = false;

        // Get a reference to the ConnectivityManager
        final ConnectivityManager manager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // Get information about the default active data network
        if (Build.VERSION.SDK_INT > 22) {
            final Network activeNetwork = manager.getActiveNetwork();
            if (activeNetwork != null) {
                final NetworkCapabilities networkCapabilities =
                        manager.getNetworkCapabilities(activeNetwork);
                result = networkCapabilities != null && (
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
            }
        } else {
            NetworkInfo info = manager.getActiveNetworkInfo();
            result = ((info != null) && (info.isConnected()));
        }
        return result;
    }

    // Displays the data received from the HTTP request
    public void displayWeather(final WeatherPOJO weather) {
        // This particular web services always returns a response object.
        // If anything goes wrong, all the fields of the object will be null but
        // main.cod, which will display the HTTP response code.
        // So, check the request was successful before updating the UI.
        if ((weather != null) && (weather.getCod() == 200)) {
            // Update the temperature
            tvTemperature.setText(String.format(getString(R.string.temperature), weather.getMain().getTemp()));
            // Update the description of the current weather
            tvDescription.setText(weather.getWeather().get(0).getDescription());
            // Update the icon representing the current weather:
            // http://openweathermap.org/weather-conditions
            int icon = R.drawable.w01d;
            switch (weather.getWeather().get(0).getId()) {

                // Thunderstorm
                case 200:
                case 201:
                case 202:
                case 210:
                case 211:
                case 212:
                case 221:
                case 230:
                case 231:
                case 232:
                    icon = R.drawable.w11d;
                    break;

                // Shower rain
                case 300:
                case 301:
                case 302:
                case 310:
                case 311:
                case 312:
                case 313:
                case 314:
                case 321:
                case 520:
                case 521:
                case 522:
                case 531:
                    icon = R.drawable.w09d;
                    break;

                // Rain
                case 500:
                case 501:
                case 502:
                case 503:
                case 504:
                    icon = R.drawable.w10d;
                    break;

                // Snow
                case 511:
                case 600:
                case 601:
                case 602:
                case 611:
                case 612:
                case 615:
                case 616:
                case 620:
                case 621:
                case 622:
                    icon = R.drawable.w13d;
                    break;

                // Mist
                case 701:
                case 711:
                case 721:
                case 731:
                case 741:
                case 751:
                case 761:
                case 762:
                case 771:
                case 781:
                    icon = R.drawable.w50d;
                    break;

                // Clear sky
                case 800:
                    icon = R.drawable.w01d;
                    break;

                // Few clouds
                case 801:
                    icon = R.drawable.w02d;
                    break;

                // Scattered clouds
                case 802:
                    icon = R.drawable.w03d;
                    break;

                // Broken clouds
                case 803:
                case 804:
                    icon = R.drawable.w04d;
                    break;
            }

            ivIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), icon, null));
        }
        // Notify the user that the request could not be completed,
        // probably the name of the city was wrong
        else {
            Toast.makeText(
                    this,
                    getString(R.string.not_found),
                    Toast.LENGTH_SHORT).show();
        }

        // Hides the indeterminate ProgressBar as the operation has finished
        pbConnecting.setVisibility(View.INVISIBLE);
        // Enable the button so the user can launch another request
        bCheckWeather.setEnabled(true);
    }

}