package com.unchesquito.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unchesquito.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Serializable> {

	@Query(nativeQuery = true, value = "SELECT * FROM users where id_user = ?1 AND id_estatus=1")
	public UserEntity findByIdUser(int idUser);

}
