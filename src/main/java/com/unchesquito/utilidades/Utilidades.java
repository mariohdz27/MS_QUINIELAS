package com.unchesquito.utilidades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class Utilidades {

	public  String obtenerErrores(BindingResult result) {
		String errores = "";
		for (ObjectError lstErrores : result.getAllErrors()) {

			errores = errores.concat("|").concat(lstErrores.getDefaultMessage());
		}
		return errores;
	}
	
	public List<String> getErrors(BindingResult result) {

		List<String> array = new ArrayList<>();

		for (FieldError lstErrors : result.getFieldErrors()) {


				array.add(lstErrors.getDefaultMessage());
			

		}
		return array;
	}

}

