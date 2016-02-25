/*
 * Copyright (c) 2016. David de Andrés and Juan Carlos Ruiz, DISCA - UPV, Development of apps for mobile devices.
 */

package labs.sdm.l0502_accessrestfulwebservice;

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
public class WeatherPOJO {

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
		
		public double getLon() {
			return lon;
		}
		public void setLon(double lon) {
			this.lon = lon;
		}
		public double getLat() {
			return lat;
		}
		public void setLat(double lat) {
			this.lat = lat;
		}
	}
	
	class System {
		private String country;
		private int sunrise;
		private int sunset;
		
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public int getSunrise() {
			return sunrise;
		}
		public void setSunrise(int sunrise) {
			this.sunrise = sunrise;
		}
		public int getSunset() {
			return sunset;
		}
		public void setSunset(int sunset) {
			this.sunset = sunset;
		}
	}
	
	class WeatherItem {
		private int id;
		private String main;
		private String description;
		private String icon;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMain() {
			return main;
		}
		public void setMain(String main) {
			this.main = main;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
	}
	
	class Main {
		private double temp;
		private double humidity;
		private double pressure;
		private double temp_min;
		private double temp_max;
		
		public double getTemp() {
			return temp;
		}
		public void setTemp(double temp) {
			this.temp = temp;
		}
		public double getHumidity() {
			return humidity;
		}
		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}
		public double getPressure() {
			return pressure;
		}
		public void setPressure(double pressure) {
			this.pressure = pressure;
		}
		public double getTemp_min() {
			return temp_min;
		}
		public void setTemp_min(double temp_min) {
			this.temp_min = temp_min;
		}
		public double getTemp_max() {
			return temp_max;
		}
		public void setTemp_max(double temp_max) {
			this.temp_max = temp_max;
		}
	}
	
	class Wind {
		private double speed;
		private double gust;
		private double deg;
		
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public double getGust() {
			return gust;
		}
		public void setGust(double gust) {
			this.gust = gust;
		}
		public double getDeg() {
			return deg;
		}
		public void setDeg(double deg) {
			this.deg = deg;
		}
	}
	
	class Clouds {
		
		private int all;

		public int getAll() {
			return all;
		}

		public void setAll(int all) {
			this.all = all;
		}
	}

	public Coordinates getCoord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	public System getSys() {
		return sys;
	}

	public void setSys(System sys) {
		this.sys = sys;
	}

	public ArrayList<WeatherItem> getWeather() {
		return weather;
	}

	public void setWeather(ArrayList<WeatherItem> weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
}