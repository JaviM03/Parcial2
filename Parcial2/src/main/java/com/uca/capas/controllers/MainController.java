package com.uca.capas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.services.CategoriaService;
import com.uca.capas.services.LibroService;

@Controller
public class MainController {
	
	@Autowired
	LibroService libroService;
	@Autowired
	CategoriaService categoriaService;

	@RequestMapping("/index")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		
		
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
		mav.setViewName("verLibro");
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
			mav.addObject("resultado", 1);
			mav.setViewName("index");
		}
		return mav;
	}
	
	@PostMapping("/saveLibro")
	public ModelAndView saveLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			List<Categoria> categorias = null;
			categorias=categoriaService.findAll();
			mav.addObject("categorias",categorias);
			mav.setViewName("libro");
		} else {
			libroService.save(libro);
			libro = new Libro();
			mav.addObject("resultado", 2);
			mav.setViewName("index");
			
			
		}
		return mav;
	}
	
	@RequestMapping("/categoria")
	public ModelAndView categorias( @ModelAttribute Categoria categoria) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("categoria", categoria);
		mav.setViewName("categoria");
		
		return mav;
	}
	@RequestMapping("/libros")
	public ModelAndView libro( @ModelAttribute Libro libro) {
		ModelAndView mav = new ModelAndView();

		
		List<Categoria> categorias = categoriaService.findAll();
		
		mav.addObject("categorias", categorias);
		mav.addObject("libro", libro);
		mav.setViewName("libro");
		
		return mav;
	}
}
