package com.unchesquito.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateBetRequest {
	
	
	@Min(value = 1, message = "El valor idUser debe de ser minimo de 1")
	private int idUser;
	
	@NotNull(message = "el valor de results debe de estar presente")
	@NotEmpty(message = "el valor de results no puede estar vacío")
	private String [] results ;

	@Min(value = 1, message = "El valor week debe de ser minimo de 1")
	private int week;
	
}
