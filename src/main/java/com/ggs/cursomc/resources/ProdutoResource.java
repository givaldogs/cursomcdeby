package com.ggs.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.cursomc.domain.Produto;
import com.ggs.cursomc.dto.ProdutoDto;
import com.ggs.cursomc.resources.utils.Url;
import com.ggs.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")

public class ProdutoResource {

	@Autowired
	ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDto>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

		String nomeDecoded = Url.decodeParam(nome);
		List<Integer> lds = Url.decodIntList(categorias);
		Page<Produto> lista = service.search(nomeDecoded, lds, page, linesPerPage, direction, orderBy);
		Page<ProdutoDto> listaDto = lista.map(obj -> new ProdutoDto(obj));
		return ResponseEntity.ok().body(listaDto);

	}
}
