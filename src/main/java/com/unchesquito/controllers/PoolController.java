package com.unchesquito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unchesquito.controllers.exceptions.http.BadRequestException;
import com.unchesquito.request.CreateBetRequest;
import com.unchesquito.response.ResponseOk;
import com.unchesquito.services.BetsService;
import com.unchesquito.utilidades.Utilidades;

import jakarta.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@CrossOrigin(origins = "*")
public class PoolController {
	
	@Autowired
	@Qualifier("BetServiceImpl")
	private BetsService betService;
	

	@Autowired
	private Utilidades utilidades;

	
	@PostMapping("/registerBet")
	public ResponseOk registerBet (@RequestBody @Valid CreateBetRequest request, BindingResult bindingResult) {
		
		log.info("Ejecutando metodo registerBet");
		log.debug("datos: "+request.toString());
		if (bindingResult.hasErrors()) {
			List<String> errors = utilidades.getErrors(bindingResult);
			log.warn("Ocurrieron los siguientes errores en la peticion: " + errors);			
			throw new BadRequestException("Los datos no cumplen con las reglas", errors);
		}

		log.info("Saliendo del metodo registerBet");
		return new ResponseOk(betService.createBet(request));
	}
}