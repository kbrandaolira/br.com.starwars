package br.com.starwars.services;

public interface IService<K,T> {

	public T execute(K k);
	
}