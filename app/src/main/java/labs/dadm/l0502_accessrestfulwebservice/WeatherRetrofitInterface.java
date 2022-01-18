/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRetrofitInterface {

    @GET("weather?APPID=MY_API_KEY&mode=json&units=metric")
    Call<WeatherPOJO> getCurrentWeather(@Query("q") String city);
}
