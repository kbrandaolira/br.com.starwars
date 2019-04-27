package br.com.starwars.services;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;

import br.com.starwars.connections.SwapiRestConnection;
import br.com.starwars.models.Planets;
import br.com.starwars.repositories.PlanetsRepository;

@Service
public class SynchronizeSwapiService implements IService<Planets,Void> {

	private static final Logger logger = LogManager.getLogger(SynchronizeSwapiService.class);
	
	@Autowired
	private PlanetsRepository planetsRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Void execute(Planets planets) {
		
		int count = 1;
		
		while(true) {
			logger.debug("Iterating by swapi id = " + count);
			
			JsonObject obj;
			try {
				obj = new SwapiRestConnection().get(count);
				
				if( obj.get("name").getAsString().equalsIgnoreCase(planets.getName()) ) {
					planets.setIdSwapi(count);
					this.planetsRepository.save(planets);
					break;
				}
				
			} catch (IOException e) {
				logger.warn("We had a problem to get planet by swapi id = " + count);
				break;
			}
			
			count ++;
		}
		
		return null;
		
	}

}
