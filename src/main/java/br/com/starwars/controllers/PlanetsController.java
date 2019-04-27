package br.com.starwars.controllers;

import java.util.List;

import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwars.models.Planets;
import br.com.starwars.repositories.PlanetsRepository;

@RestController
@RequestMapping("/v0/planets")
public class PlanetsController {

	@Autowired
	private PlanetsRepository planetsRepository;
	
	// Create Planet
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Planets> create(@Valid @RequestBody Planets planets) {
		planets.set_id(ObjectId.get());
		return new ResponseEntity<>(this.planetsRepository.save(planets), HttpStatus.CREATED);
	}
	
	// Get All Planets
	
	 @RequestMapping(value = "/", method = RequestMethod.GET) 
	 public ResponseEntity<List<Planets>> findAll() { 
		 return new ResponseEntity<>(this.planetsRepository.findAll(), HttpStatus.OK);
	}
	
	// Get Planets by Name

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<Planets> findByName(@RequestParam("name") String name) {
		Planets planet = this.planetsRepository.findByName(name);
		
		if( planet != null ) {
			return new ResponseEntity<>(planet, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Get Planets by Id

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public ResponseEntity<Planets> findBy_id(@PathVariable("id") ObjectId id) throws Exception { 
		Planets planet = this.planetsRepository.findBy_id(id);
		
		if( planet != null ) {
			return new ResponseEntity<>(planet, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Delete Planet
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		Planets planet = this.planetsRepository.findBy_id(new ObjectId(id));
		
		if( planet != null ) {
			this.planetsRepository.delete(planet);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
