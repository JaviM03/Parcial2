package com.uca.capas.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Categoria;

public interface CategoriaService {

	public List<Categoria> findAll() throws DataAccessException;

	public Categoria findOne(Integer c_categoria) throws DataAccessException;
	
	public void save(Categoria categoria) throws DataAccessException;

}
