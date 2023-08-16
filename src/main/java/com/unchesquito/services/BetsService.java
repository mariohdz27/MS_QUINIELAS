package com.unchesquito.services;

import com.unchesquito.request.CreateBetRequest;

public interface BetsService {

	public abstract boolean createBet (CreateBetRequest bet);
}
