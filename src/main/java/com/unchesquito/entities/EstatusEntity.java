package com.unchesquito.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Table
@Entity(name = "estatus")
@Data
@ToString
public class EstatusEntity {

	@Id
	@Column(name = "id_estatus")
	private int idEstatus;
	
	@Column
	private String descripcion;
	
	
}
