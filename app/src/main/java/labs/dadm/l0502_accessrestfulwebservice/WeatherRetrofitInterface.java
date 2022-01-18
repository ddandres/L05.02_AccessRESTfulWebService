/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRetrofitInterface {

    @GET("weather?APPID=879247f3f3bda41f02246d88e5b6deaa&mode=json&units=metric")
    Call<WeatherPOJO> getCurrentWeather(@Query("q") String city);
}
