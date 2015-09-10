package br.com.julianfernando.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CarroRepository {
	@PersistenceContext 
	private EntityManager entityManager;
	
	public CarroRepository(EntityManager manager) {
		this.setEntityManager(manager);
	}

	public void adiciona(Carro carro) {
		this.entityManager.persist(carro);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos() {
		Query query = this.entityManager.createQuery("SELECT c FROM Carro c");
		return query.getResultList();
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
