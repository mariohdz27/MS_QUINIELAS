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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Setter
@Getter
public class UserEntity {
	
	@Id
	@Column(name = "id_user")
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;

	@Column(name = "user")
	@NotNull
	private String user;

	@Column(name = "email")
	@NotNull
	private String email;
	
	@Column(name = "telephone")
	@NotNull
	private String telephone;
	
	@Column(name = "password")
	@NotNull
	private String password;

	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	
	@Column(name = "birthdate")
	@NotNull
	private Date birthdate;
	
	@Column(name = "registration_date")
	@NotNull
	private Date registrationDate;
	
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@NotNull
	@JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
	private EstatusEntity idEstatus;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@NotNull
	@JoinColumn(name = "id_avatar", referencedColumnName = "id_team")
	private TeamsEntity idAvatar;
	
	
	
}
