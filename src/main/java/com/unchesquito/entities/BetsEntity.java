package com.unchesquito.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Table
@Entity(name = "bets")
@Builder
public class BetsEntity {


	@Id
	@Column(name = "id_bets")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBets;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@NotNull
	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	private UserEntity iduser;

	@Column
	@NotNull
	private String bet;
	
	@Column
	private int week;
	
	@Column(name = "creation_date")
	@NotNull
	private Date creationDate;
	
}
