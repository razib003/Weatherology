package com.weatherology.weatherology.forecast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class WunderGround {
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String API_KEY = "api/3550b87a0edf2033/conditions/q/";
	private static final String URI = "http://api.wunderground.com/";
	private int zip;
	private double temperature;
	private double humidity;

	public WunderGround(int zip) {
		this.zip = zip;
		try {
			//System.out.println(this.readUrl(URI+"33330,us"+API_KEY));
			//System.out.println(this.readUrl(URI+API_KEY+"33330.json"));
			forecast();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getTemperature() {
		//return 0.0;
		return temperature;
	}

	public double getHumidity() {
		//return 0.0;
		return humidity;
	}

	public String forecast() {
		String urlString = URI + API_KEY + zip + ".json";
		JsonObject jsonObject = null;

		try {
			jsonObject = new JsonParser().parse(this.readUrl(urlString)).getAsJsonObject();
			temperature = jsonObject.get("current_observation").getAsJsonObject().get("temp_f").getAsDouble();
			String strhumidity = jsonObject.get("current_observation").getAsJsonObject().get("relative_humidity").getAsString();
			humidity = Double.parseDouble(strhumidity.substring(0, strhumidity.length()-1));
			System.out.println("\n>>>>>>>>>>temp razib:"+temperature+":"+humidity);
			/*
			 * test razib
			JsonObject jobj = jsonObject.get("current_observation").getAsJsonObject();
			double dd = jobj.get("temp_f").getAsDouble();
			System.out.println("\n>>>>>>>>>>temp razib:"+dd);
			JsonElement jsonElement = jsonObject.get("current_observation").getAsJsonObject();
			if (jsonElement.isJsonObject()) {
				
				Set<Entry<String, JsonElement>> ens = ((JsonObject) jsonElement).entrySet();
		        if (ens != null) {
		                for (Entry<String, JsonElement> en : ens) {
		                    System.out.println(++j + ":" +en.getKey() + " : "+en.getValue());
		                }
		        }
				
			} */
		        
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	private static String readUrl(String urlString) throws Exception {
        URL obj = new URL(urlString);
        HttpURLConnection httpConn = (HttpURLConnection) obj.openConnection();

        httpConn.setRequestMethod("GET");
        httpConn.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpConn.getResponseCode();
        if (responseCode == 200) {
             BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

             String responseLine;
             StringBuffer response = new StringBuffer();

             while ((responseLine = responseReader.readLine()) != null) {
                 response.append(responseLine + "\n");
             }

             responseReader.close();

             return response.toString();
        }
        return null;
	}

}
