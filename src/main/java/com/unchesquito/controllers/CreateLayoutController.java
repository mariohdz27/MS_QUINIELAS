package com.unchesquito.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.unchesquito.entities.TeamsEntity;
import com.unchesquito.controllers.exceptions.http.BadRequestException;
import com.unchesquito.request.LayoutRequest;
import com.unchesquito.response.QuinielaResponse;
import com.unchesquito.response.ResponseOk;
import com.unchesquito.services.EquiposService;
import com.unchesquito.services.QuinielasService;
import com.unchesquito.services.impl.QuinielaServiceImpl;
import com.unchesquito.utilidades.Utilidades;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@CrossOrigin(origins = "*")
public class CreateLayoutController {

	@Autowired
	@Qualifier("EquiposServiceImpl")
	private EquiposService equiposService;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	@Qualifier("QuinielaWebServiceImpl")
	private QuinielasService quinielaService;

	@PostMapping("/create-layout")
	public ResponseOk createLayuout(@RequestBody @Valid LayoutRequest request, BindingResult bindingResult,
			HttpServletResponse response) throws IOException {

		log.info("Entrando al metodo createLayuout");
		log.debug("Datos: " + request.toString());
		
		if (bindingResult.hasErrors()) {
			List<String> errors = utilidades.getErrors(bindingResult);
			log.warn("Ocurrieron los siguientes errores en la peticion: " + errors);			
			throw new BadRequestException("Los datos no cumplen con las reglas", errors);
		}

		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		QuinielaServiceImpl generator = new QuinielaServiceImpl(request);
		generator.generate(response);
		
		log.info("Saliendo del metodo createLayuout");
		return new ResponseOk(true);

	}

	@PostMapping("/create-web")
	public ResponseOk createLayuoutWeb(@RequestBody @Valid LayoutRequest request, BindingResult bindingResult,
			HttpServletResponse response) throws IOException, ParseException {

		log.info("Entrando al metodo createLayuoutWeb");
		log.debug("Datos: " + request.toString());

		
		if (bindingResult.hasErrors()) {
			List<String> errors = utilidades.getErrors(bindingResult);
			log.warn("Ocurrieron los siguientes errores en la peticion: " + errors);			
			throw new BadRequestException("Los datos no cumplen con las reglas", errors);
		}

		quinielaService.createQuiniela(request);
		log.info("Saliendo del metodo createLayuout createLayuoutWeb");
		return new ResponseOk(true);

	}

	@GetMapping("get-layout")
	public QuinielaResponse getQuiniela()  {
		log.info("Entrando al metodo getQuiniela");
		QuinielaResponse response = quinielaService.getQuiniela();
		log.info("Saliendo del metodo getQuiniela");
		return response;
	}

	@GetMapping("/get-teams")
	public List<TeamsEntity> getTeams() {
		log.info("Entrando al metodo getTeams");
		List<TeamsEntity> response = equiposService.getTeams();
		log.info("Saliendo del metodo getTeams");
		return response;
	}

}
