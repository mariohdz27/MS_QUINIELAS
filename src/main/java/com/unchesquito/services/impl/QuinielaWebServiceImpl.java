package com.unchesquito.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unchesquito.controllers.exceptions.http.InternalServerException;
import com.unchesquito.entities.EstatusEntity;
import com.unchesquito.entities.QuinielaEntity;
import com.unchesquito.entities.TeamsEntity;
import com.unchesquito.repository.EstatusRepository;
import com.unchesquito.repository.QuinielaRepository;
import com.unchesquito.repository.TeamsRepository;
import com.unchesquito.request.LayoutRequest;
import com.unchesquito.request.MatchesRequest;
import com.unchesquito.response.QuinielaResponse;
import com.unchesquito.services.QuinielasService;

import lombok.extern.apachecommons.CommonsLog;

@Service("QuinielaWebServiceImpl")
@CommonsLog
public class QuinielaWebServiceImpl implements QuinielasService {

	@Autowired
	@Qualifier("QuinielaRepository")
	private QuinielaRepository quinielaRepository;

	@Autowired
	@Qualifier("EstatusRepository")
	private EstatusRepository estatusRepository;

	@Autowired
	@Qualifier("TeamsRepository")
	private TeamsRepository teamsRepository;

	@Override
	public QuinielaEntity createQuiniela(LayoutRequest request) throws ParseException {

		log.info("Entrando al metodo createQuiniela");

		ObjectMapper mapper = new ObjectMapper();
		String jsonQuiniela = "";

		EstatusEntity estatus = new EstatusEntity();
		estatus.setIdEstatus(1);

		QuinielaEntity lastQuiniela = null;

		try {
			log.info("Buscando la quiniela de la semana pasada para actualizar su estatus");
			lastQuiniela = quinielaRepository.findByIdEstatus(estatus);

		} catch (Exception e) {

			log.error("Ocurrio un error al obtener la ultima quiniela " + e.getMessage());
			throw new InternalServerException(e.getMessage());
		}

		if (lastQuiniela != null) {
			EstatusEntity estatusUpdate = new EstatusEntity();
			estatusUpdate.setIdEstatus(2);
			lastQuiniela.setIdEstatus(estatusUpdate);
			try {
				quinielaRepository.save(lastQuiniela);
				log.info("Estatus actualizado");

			} catch (Exception e) {
				log.error("No se puedo actualizar el estatus de la ultima quiniela " + e.getMessage());
				throw new InternalServerException(e.getMessage());
			}
		}

		log.info("Obteniendo la informacion de los equipos");
		List<TeamsEntity> teams = new ArrayList<TeamsEntity>();
		try {
			teams = teamsRepository.findByIdEstatus(estatus);

		} catch (Exception e) {
			log.error("No se pudo obtener los equipos " + e.getMessage());
			throw new InternalServerException(e.getMessage());
		}
		HashMap<Integer, String> mapTeams = new HashMap<>();

		for (TeamsEntity teamsEntity : teams) {
			mapTeams.put(teamsEntity.getIdTeam(), teamsEntity.getTeamName());
		}

		for (int i = 0; i < request.getMatches().length; i++) {

			request.getMatches()[i].setLocal(mapTeams.get(request.getMatches()[i].getIdLocal()));
			request.getMatches()[i].setVisitor(mapTeams.get(request.getMatches()[i].getIdVisitor()));

		}

		try {
			jsonQuiniela = mapper.writeValueAsString(request.getMatches());
		} catch (JsonProcessingException e) {
			log.error("Ocurrio un error al parsear los partidos");
			throw new InternalServerException(e.getMessage());
		}

		QuinielaEntity quiniela = QuinielaEntity.builder().week(request.getWeek()).idEstatus(estatus)
				.matches(jsonQuiniela).deadline(new SimpleDateFormat("yyyy-MM-dd").parse(request.getDeadline()))
				.cost(request.getCost()).build();

		QuinielaEntity response;
		
		try {
			response = quinielaRepository.save(quiniela);
		} catch (Exception e) {
			log.error("Ocurrio un error al guardar la quiniela de la semana");
			throw new InternalServerException(e.getMessage());
		}
		
		log.info("Saliendo del metodo createQuiniela");
		return response;
	}

	@Override
	public QuinielaResponse getQuiniela() {
		log.info("Entrando al metodo getQuiniela");
		ObjectMapper mapper = new ObjectMapper();
		EstatusEntity estatusEntity = null;
		QuinielaEntity quinielaEntity = null;
		
		try {
			estatusEntity = this.estatusRepository.findByIdEstatus(1);
			
		} catch (Exception e) {
			log.error("No se puedo obtener la entidad estatus "+ e.getMessage());
			throw new InternalServerException(e.getMessage());
		}
		
		try {
			log.info("Obteniendo los datos de la quiniela");
			quinielaEntity = this.quinielaRepository.findByIdEstatus(estatusEntity);
			
		} catch (Exception e) {
			
			log.error("No se puedo la informacion de la quiniela "+ e.getMessage());			
			throw new InternalServerException(e.getMessage());

		}
		MatchesRequest [] matches ;
		HashMap<Integer, String> mapTeams = new HashMap<>();
		try {
			log.info("Obteniendo informacion de los equipos");
			List<TeamsEntity> teams = teamsRepository.findByIdEstatus(estatusEntity);
			for (TeamsEntity teamsEntity : teams) {
				mapTeams.put(teamsEntity.getIdTeam(), new String(teamsEntity.getImage()));
			}

		} catch (Exception e) {
			log.error("Ocurrio un error al obtener la informacion de los equipos " + e.getMessage());
			throw new InternalServerException(e.getMessage());
		}

		try {
			matches = mapper.readValue(quinielaEntity.getMatches(), MatchesRequest[].class);
			for (int i = 0; i < matches.length; i++) {

				matches[i].setImageLocal(mapTeams.get(matches[i].getIdLocal()));
				matches[i].setImageVisitor(mapTeams.get(matches[i].getIdVisitor()));
			}

		} catch (JsonProcessingException e) {
			log.error("Error al parsear la propiedad matches");
			throw new InternalServerException(e.getMessage());
		}
		log.info("Saliendo del metodo getQuiniela");
		return QuinielaResponse.builder().week(quinielaEntity.getWeek()).matches(matches)
				.deadline(quinielaEntity.getDeadline()).cost(quinielaEntity.getCost()).build();

	}

}
