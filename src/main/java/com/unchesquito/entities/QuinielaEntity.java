package com.unchesquito.entities;

import java.util.Date;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity(name = "quinielas")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class QuinielaEntity {

	@Id
	@Column(name = "id_quinielas")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idQuinielas;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstatusEntity idEstatus;
	
	@Column(name = "week")
	@NotNull
	private int week;
	
	@NotNull
	@Column(name = "matches", columnDefinition = "json")
	private String matches;
	
	@Column(name = "deadline")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date deadline; 
	
	@Column(name="cost")
	@NotNull
	private String cost;	
}