package br.com.starwars.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.starwars.models.Planets;

public interface PlanetsRepository extends MongoRepository<Planets, String> {

	Planets findBy_id(ObjectId id);
	
	Planets findByName(String name);

}