/*
 * Copyright (c) 2019. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/*
 * Launches a GET request to get the current weather.
 * Data is processed using Gson to obtain a WeatherPOJO object.
 */
public class WeatherRequest extends Request<WeatherPOJO> {

    final private Response.Listener<WeatherPOJO> listener;

    public WeatherRequest(String url, Response.Listener<WeatherPOJO> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;
    }

    /*
     * This method will be called whenever the request obtains a response.
     */
    @Override
    protected Response<WeatherPOJO> parseNetworkResponse(NetworkResponse response) {

        try {
            // Convert the obtained byte[] into a String
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            // Use Gson to process the JSON string and return a WeatherPOJO object
            return Response.success(new Gson().fromJson(json, WeatherPOJO.class),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * This method will be called to deliver the processed response to the attached listener.
     */
    @Override
    protected void deliverResponse(WeatherPOJO response) {
        this.listener.onResponse(response);
    }

}
