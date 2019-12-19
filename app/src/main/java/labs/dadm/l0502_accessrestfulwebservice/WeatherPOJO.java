/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.dadm.l0502_accessrestfulwebservice;

import java.util.ArrayList;

/* Sample response in JSON format
{
	"coord":{"lon":-0.37739,"lat":39.469749},
	"sys":{"country":"ES","sunrise":1381817529,"sunset":1381857726},
	"weather":[
		{"id":800,"main":"Clear","description":"Sky is Clear","icon":"01d"}
	],
	"base":"gdps stations",
	"main":{"temp":28.71,"humidity":33,"pressure":1011,"temp_min":27.78,"temp_max":30},
	"wind":{"speed":3.6,"gust":11.31,"deg":248},
	"clouds":{"all":0},
	"dt":1381849423,
	"id":2509954,
	"name":"Valencia",
	"cod":200
}
*/
class WeatherPOJO {

    private Coordinates coord;
    private System sys;
    private ArrayList<WeatherItem> weather;
    private String base;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private int id;
    private String name;
    private int cod;

    class Coordinates {
        private double lon;
        private double lat;

        double getLon() {
            return lon;
        }

        void setLon(double lon) {
            this.lon = lon;
        }

        double getLat() {
            return lat;
        }

        void setLat(double lat) {
            this.lat = lat;
        }
    }

    class System {
        private String country;
        private int sunrise;
        private int sunset;

        String getCountry() {
            return country;
        }

        void setCountry(String country) {
            this.country = country;
        }

        int getSunrise() {
            return sunrise;
        }

        void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        int getSunset() {
            return sunset;
        }

        void setSunset(int sunset) {
            this.sunset = sunset;
        }
    }

    class WeatherItem {
        private int id;
        private String main;
        private String description;
        private String icon;

        int getId() {
            return id;
        }

        void setId(int id) {
            this.id = id;
        }

        String getMain() {
            return main;
        }

        void setMain(String main) {
            this.main = main;
        }

        String getDescription() {
            return description;
        }

        void setDescription(String description) {
            this.description = description;
        }

        String getIcon() {
            return icon;
        }

        void setIcon(String icon) {
            this.icon = icon;
        }
    }

    class Main {
        private double temp;
        private double humidity;
        private double pressure;
        private double temp_min;
        private double temp_max;

        double getTemp() {
            return temp;
        }

        void setTemp(double temp) {
            this.temp = temp;
        }

        double getHumidity() {
            return humidity;
        }

        void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        double getPressure() {
            return pressure;
        }

        void setPressure(double pressure) {
            this.pressure = pressure;
        }

        double getTemp_min() {
            return temp_min;
        }

        void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        double getTemp_max() {
            return temp_max;
        }

        void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }
    }

    class Wind {
        private double speed;
        private double gust;
        private double deg;

        double getSpeed() {
            return speed;
        }

        void setSpeed(double speed) {
            this.speed = speed;
        }

        double getGust() {
            return gust;
        }

        void setGust(double gust) {
            this.gust = gust;
        }

        double getDeg() {
            return deg;
        }

        void setDeg(double deg) {
            this.deg = deg;
        }
    }

    class Clouds {

        private int all;

        int getAll() {
            return all;
        }

        void setAll(int all) {
            this.all = all;
        }
    }

    Coordinates getCoord() {
        return coord;
    }

    void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    System getSys() {
        return sys;
    }

    void setSys(System sys) {
        this.sys = sys;
    }

    ArrayList<WeatherItem> getWeather() {
        return weather;
    }

    void setWeather(ArrayList<WeatherItem> weather) {
        this.weather = weather;
    }

    String getBase() {
        return base;
    }

    void setBase(String base) {
        this.base = base;
    }

    Main getMain() {
        return main;
    }

    void setMain(Main main) {
        this.main = main;
    }

    Wind getWind() {
        return wind;
    }

    void setWind(Wind wind) {
        this.wind = wind;
    }

    Clouds getClouds() {
        return clouds;
    }

    void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    int getDt() {
        return dt;
    }

    void setDt(int dt) {
        this.dt = dt;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getCod() {
        return cod;
    }

    void setCod(int cod) {
        this.cod = cod;
    }
}