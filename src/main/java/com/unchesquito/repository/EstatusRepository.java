package com.unchesquito.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unchesquito.entities.EstatusEntity;

@Repository("EstatusRepository")
public interface EstatusRepository extends JpaRepository<EstatusEntity, Serializable>{
	
	public abstract EstatusEntity findByIdEstatus(int idEstatus);

}
