package com.unchesquito.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unchesquito.entities.TeamsEntity;
import com.unchesquito.entities.EstatusEntity;


@Repository("TeamsRepository")
public interface TeamsRepository extends JpaRepository<TeamsEntity, Serializable>{

	
	public abstract List<TeamsEntity> findByIdEstatus(EstatusEntity estatus);
	
	
}
