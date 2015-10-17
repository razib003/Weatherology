package com.weatherology.weatherology.forecast;

public class WunderGroundAdapter extends WeatherProviderAdapter {
	private final static String name = "WunderGround";
	private WunderGround wunderGround;
	
	public WunderGroundAdapter(WunderGround wunderGround) {
		//name = "AccuWeather";
		this.wunderGround = wunderGround;
	}
	
	public String getName() {
		return WunderGroundAdapter.name;
		//return name;
	}
	
	public String getForecast() {
		return this.wunderGround.forecast();
	}
	
	public double getTemperature() {
		return this.wunderGround.getTemperature();
	}
	
	public double getHumidity() {
		return this.wunderGround.getHumidity();
	}
}
