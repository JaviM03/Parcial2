package com.uca.capas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.services.CategoriaService;
import com.uca.capas.services.LibroService;

public class MainController {
	
	@Autowired
	LibroService libroService;
	@Autowired
	CategoriaService categoriaService;

	@RequestMapping("/index")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		
		Libro libro=new Libro();
		List<Categoria> categorias= null;
		categorias=categoriaService.findAll();
		
		mav.addObject("libros", libro);
		mav.addObject("categoria",categorias);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/verLibros")
	public ModelAndView findAll() {
		ModelAndView mav=new ModelAndView();
		List<Libro> libros= null;
		try {
			libros= libroService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("libros",libros);
		mav.setViewName("listaLibros");
		return mav;
	}
	
	@PostMapping("/saveCategoria")
	public ModelAndView saveCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();

		if(result.hasErrors()) {
			mav.setViewName("ingresarCategoria");
		} else {
			categoriaService.save(categoria);
			List<Categoria> categorias =null;
			
			mav.addObject("categoria",categoria);
			mav.setViewName("resultado");
		}
		return mav;
	}
	
	@PostMapping("/saveLibro")
	public ModelAndView saveLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<Categoria> categorias= null;
		categorias=categoriaService.findAll();
		if(result.hasErrors()) {
			mav.setViewName("agregarLibro");
		} else {
			libroService.save(libro);
			List<Libro> libros =null;
			
			mav.addObject("libro",libro);
			mav.addObject("categorias", categorias);
			mav.setViewName("resultado");
		}
		return mav;
	}
}
