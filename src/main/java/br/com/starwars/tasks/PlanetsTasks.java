package br.com.starwars.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.starwars.models.Planets;
import br.com.starwars.repositories.PlanetsRepository;
import br.com.starwars.services.SynchronizeSwapiService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class PlanetsTasks {
	
	private static final Logger logger = LogManager.getLogger(PlanetsTasks.class);

	@Autowired
	private PlanetsRepository planetsRepository;
	
	@Autowired
	private SynchronizeSwapiService synchronizeSwapiService;
	
	// Every minute synchronize with swapi to update movie appearances

	@Scheduled(fixedDelay = 60000)
	public void synchronizeSwapi() {
		logger.debug("Starting synchronizing with Swapi");
		
		for( Planets planets : this.planetsRepository.findAll() ) {
			if( planets.getIdSwapi() == 0 ) {
				this.synchronizeSwapiService.execute(planets);
			}
		}
	}
	
}
