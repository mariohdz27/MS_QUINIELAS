package com.unchesquito.request;




import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LayoutRequest {
	
	@NotNull(message = "El campo 'fechaLimite' es obligatorio")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String deadline;
	
	@Min(value = 0)
	private int week;
	
	@NotNull(message = "El campo 'costo' es obligatorio")
	private String cost;

	@NotNull(message = "El campo 'partidos' es obligatorio")
	private MatchesRequest [] matches;

}

