package com.unchesquito.response;

import java.util.Date;

import com.unchesquito.request.MatchesRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuinielaResponse {
	

	private int week;
		
	private Date deadline; 
	
	private String cost;
	
	private MatchesRequest [] matches;


}
