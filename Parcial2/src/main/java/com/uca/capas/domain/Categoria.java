package com.uca.capas.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(schema="public",name="cat_categoria")
public class Categoria {

	@Id
	@Column(name="c_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer c_categoria;
	
	@Column(name="s_categoria")
	private String s_categoria;


	public Integer getC_categoria() {
		return c_categoria;
	}
	
	@OneToMany(mappedBy="categoria",fetch=FetchType.EAGER)
	private List<Libro> libros;


	public List<Libro> getLibros() {
		return libros;
	}


	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}


	public void setC_categoria(Integer c_categoria) {
		this.c_categoria = c_categoria;
	}


	public String getS_categoria() {
		return s_categoria;
	}


	public void setS_categoria(String s_categoria) {
		this.s_categoria = s_categoria;
	}
	
	
	
	public Categoria() {
		
		
	}

}
