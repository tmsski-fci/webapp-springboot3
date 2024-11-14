package br.mackenzie.webapp.professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="professores")
public class Professor {

	@Id @GeneratedValue
	private long id;

	private String nome;
	private int matricula;
	private String area;
		
	public Professor() {
		super();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}


}
