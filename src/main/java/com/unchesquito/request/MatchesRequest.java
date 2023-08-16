package com.unchesquito.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MatchesRequest {

	@Min(value = 1, message = "El valor de local debe de ser minimo de 1")
	private int idLocal;

	@Min(value = 1, message = "El valor de visitante debe de ser minimo de 1")
	private int idVisitor;
	
	
	private String imageLocal;
	private String imageVisitor;
	
	
	private String local;
	private String visitor;

}
