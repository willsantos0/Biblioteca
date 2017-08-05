package com.biblioteca.util.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Configuration;

// Singleton
@Configuration
public class EntityManagerProducer {

	private static EntityManagerFactory factory = null;
	private static EntityManager em = null;
	private static EntityManagerProducer instance = new EntityManagerProducer();

	private EntityManagerProducer() {
	}
	
	public static EntityManagerProducer getInstance(){
		return instance;
	}

	@Produces
	@RequestScoped
	public synchronized static EntityManager create() {
		try{
			if(factory == null)
				factory = Persistence.createEntityManagerFactory("bibliotecaPU");
				
			em = factory.createEntityManager();
			
			
		}catch(Exception e){
			
		}
		return em;
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}

}
