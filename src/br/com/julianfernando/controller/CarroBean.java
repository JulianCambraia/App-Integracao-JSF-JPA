package br.com.julianfernando.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.julianfernando.model.Carro;
import br.com.julianfernando.model.CarroRepository;

@ManagedBean
@SessionScoped
public class CarroBean {
	
	private Carro carro = new Carro();
	private List<Carro> carros;
	
	public void adicionaCarro() {
			EntityManager entityManager = this.getEntityManager();
			CarroRepository carroRepository = new CarroRepository(entityManager);
			
			carroRepository.adiciona(this.carro);
			this.carro = new Carro();
			this.carros = null;
	}

	public List<Carro> getCarros() {
		if (this.carros == null) {
			EntityManager entityManager = this.getEntityManager();
			CarroRepository repository = new CarroRepository(entityManager);
			this.carros = repository.buscaTodos();
		}
		
		return this.carros;
	}
	
	private EntityManager getEntityManager() {
		// recupera o Entity Manager encapsulado no Filter
		// O EntityManager criado no filtro é colocado no escopo de Request. Dessa forma, você não pode injetá-lo em um ManagedBean com escopo maior do que request. 
		// Verifique a especificação do JSF 2:
		
	    ELContext elContext = FacesContext.getCurrentInstance().getELContext();  
        EntityManager entityManager = (EntityManager)  FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "entityManager");  
		return entityManager;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
}
