package com.unchesquito.services.impl;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unchesquito.controllers.exceptions.http.InternalServerException;
import com.unchesquito.controllers.exceptions.http.NotFoundException;
import com.unchesquito.entities.BetsEntity;
import com.unchesquito.entities.UserEntity;
import com.unchesquito.repository.BetsRepository;
import com.unchesquito.repository.UserRepository;
import com.unchesquito.request.CreateBetRequest;
import com.unchesquito.services.BetsService;

import lombok.extern.apachecommons.CommonsLog;

@Service("BetServiceImpl")
@CommonsLog
public class BetServiceImpl implements BetsService {

	@Autowired
	private BetsRepository betsRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean createBet(CreateBetRequest bet) {

		log.info("Entrando al metodo createBet");
		UserEntity user;
		try {

			user = userRepository.findByIdUser(bet.getIdUser());

		} catch (Exception e) {
			log.error("Ocurrio un error al buscar datos del usuario " +e.getMessage());

			throw new InternalServerException(e.getMessage());
		}

		if (user == null) {
			log.warn("El usuario " + bet.getIdUser() +" no existe ! Este flujo no es normal");

			throw new NotFoundException("El usuario no existe");

		}

		log.info("Usuario encontrado");
		
		String bets = Arrays.toString(bet.getResults());
		BetsEntity betEntity = BetsEntity.builder()
				.iduser(user)
				.bet(bets)
				.week(bet.getWeek())
				.creationDate(new Date())
				.build();

		try {
			betsRepository.save(betEntity);

			log.info("Quiniela creada");

		} catch (Exception e) {
			log.error("Ocurrio un error al tratar de guardar la quiniela " +e.getMessage());
			throw new InternalServerException(e.getMessage());
		}
		log.info("Saliendo del metodo createBet");
		return true;
	}

}
