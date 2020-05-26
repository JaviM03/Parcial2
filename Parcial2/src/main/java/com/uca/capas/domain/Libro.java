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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema="public",name="cat_libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="c_libro")
	private Integer c_libro;
	
	
	@Max(value=500,message="Este campo tiene una maximo de 500 caracteres")
	@NotEmpty(message="Este campo no puede ser vacio")
	@Column(name="s_titulo")
	private String s_titulo;
	
	@Max(value=150,message="Este campo tiene una maximo de 150 caracteres")
	@NotEmpty(message="Este campo no puede ser vacio")
	@Column(name="s_autor")
	private String s_autor;
	
	@Transient
	private Integer c_categoria;
	
	@Temporal(TemporalType.DATE)
	@Column(name="f_ingreso")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date f_ingreso;
	
	@Column(name="b_estado")
	private Boolean estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_categoria")
	private Categoria categoria;

	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getC_libro() {
		return c_libro;
	}

	public void setC_libro(Integer c_libro) {
		this.c_libro = c_libro;
	}

	public String getS_titulo() {
		return s_titulo;
	}

	public void setS_titulo(String s_titulo) {
		this.s_titulo = s_titulo;
	}

	public String getS_autor() {
		return s_autor;
	}

	public void setS_autor(String s_autor) {
		this.s_autor = s_autor;
	}

	public Integer getC_categoria() {
		return c_categoria;
	}

	public void setC_categoria(Integer c_categoria) {
		this.c_categoria = c_categoria;
	}

	public Date getF_ingreso() {
		return f_ingreso;
	}

	public void setF_ingreso(Date f_ingreso) {
		this.f_ingreso = f_ingreso;
	}

	public boolean isB_estado() {
		return estado;
	}

	public void setB_estado(boolean b_estado) {
		this.estado = b_estado;
	}
	

	
	public Libro() {
		
	}
	
	public String getEstadoDelegate() {
		if(this.estado == null) 
			return"";
		else {
			return estado == true?"Ativo":"Inactivo";
		}
	}

}
