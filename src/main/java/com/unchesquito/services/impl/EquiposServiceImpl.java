package com.unchesquito.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unchesquito.entities.TeamsEntity;
import com.unchesquito.controllers.exceptions.http.InternalServerException;
import com.unchesquito.entities.EstatusEntity;
import com.unchesquito.repository.TeamsRepository;
import com.unchesquito.services.EquiposService;

import lombok.extern.apachecommons.CommonsLog;

@Service("EquiposServiceImpl")
@CommonsLog
public class EquiposServiceImpl implements EquiposService {

	@Autowired
	@Qualifier("TeamsRepository")
	private TeamsRepository teamsRepository;
	
	
	
	@Override
	public List<TeamsEntity> getTeams() {
		log.info("Entrando al metodo getTeams");
		
		EstatusEntity estatus = new EstatusEntity();
		estatus.setIdEstatus(1);
		
		List<TeamsEntity> response = new ArrayList<>();
		try {
			log.info("Buscando listado de equipos");
			response = teamsRepository.findByIdEstatus(estatus);
		} catch (Exception e) {
			log.error("No se puedo obtener el listado de equipos " + e.getMessage());
			throw new InternalServerException(e.getMessage());
		}
		
		log.info("Saliendo del metodo getTeams");
		return response;
	}

}
