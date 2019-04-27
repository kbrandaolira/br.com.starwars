package br.com.starwars.connections;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestConnection {

	public HttpURLConnection getConnection(String path) throws IOException {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept","application/json");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		connection.connect();
		return connection;
	}
	
}