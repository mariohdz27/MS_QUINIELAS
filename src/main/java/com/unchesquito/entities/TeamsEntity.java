package com.unchesquito.entities;


import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table
@Entity(name = "teams")
@Data
@NoArgsConstructor()
public class TeamsEntity {

	
	@Id
	@Column(name = "id_team")
	@NotNull
	private int idTeam;

	@Column(name = "team_name")
	@NotNull
	private String teamName;
	
	@Column(name = "image", columnDefinition = "MEDIUMBLOB", length = 16777215)
	@NotNull
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	@NotNull
	private EstatusEntity idEstatus;
	
	
	
}
