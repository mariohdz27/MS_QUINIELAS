package com.unchesquito.services;

import java.text.ParseException;

import com.unchesquito.entities.QuinielaEntity;
import com.unchesquito.request.LayoutRequest;
import com.unchesquito.response.QuinielaResponse;

public interface QuinielasService {

	public abstract QuinielaEntity createQuiniela(LayoutRequest request) throws ParseException;
	
	public abstract QuinielaResponse getQuiniela() ;
}
