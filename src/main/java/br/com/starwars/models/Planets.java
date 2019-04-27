package br.com.starwars.models;

import java.io.IOException;

import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.starwars.connections.SwapiRestConnection;

public class Planets {
	
	private static final Logger logger = LogManager.getLogger(Planets.class);

	@Id
	private ObjectId _id;
	
	@NotEmpty(message = "Name is required") 
	private String name;
	
	@NotEmpty(message = "Climate is required")
	private String climate;
	
	@NotEmpty(message = "Terrain is required")
	private String terrain;
	
	private int idSwapi;
	
	public Planets(String _id, String name, String climate, String terrain) {
		this._id = new ObjectId(_id);
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
	
	public Planets() {}
	
	@Transient
	public int getMoviesAppearances() {
		int n = -1;
		try {
			n = new SwapiRestConnection().getMoviesAppearancesBy(this.idSwapi);
		} catch( IOException e ) {
			logger.warn("We had a problem to get movies appearances by swapi id = " + this.idSwapi);
		}
		return n;
	}
	
	public String get_id() {
		return _id.toHexString();
	}
	
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClimate() {
		return climate;
	}
	
	public void setClimate(String climate) {
		this.climate = climate;
	}
	
	public String getTerrain() {
		return terrain;
	}
	
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
	public int getIdSwapi() {
		return idSwapi;
	}
	
	public void setIdSwapi(int idSwapi) {
		this.idSwapi = idSwapi;
	}
	
}
