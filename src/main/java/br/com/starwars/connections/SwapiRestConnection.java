package br.com.starwars.connections;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SwapiRestConnection {
	
	private static final String SWAPI_URL = "https://swapi.co/api/";
	
	public JsonObject getAll() throws IOException {
		HttpURLConnection connection = new RestConnection().getConnection(SWAPI_URL + "planets/");
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
		return jsonElement.getAsJsonObject();
	}
	
	public JsonObject get(int id) throws IOException {
		HttpURLConnection connection = new RestConnection().getConnection(SWAPI_URL + "planets/" + id);
		JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
		return jsonElement.getAsJsonObject();
	}
	
	public int getMoviesAppearancesBy(int id) throws IOException {
		return this.get(id).get("films").getAsJsonArray().size();
	}

}
