package com.unchesquito.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unchesquito.entities.EstatusEntity;
import com.unchesquito.entities.QuinielaEntity;

@Repository("QuinielaRepository")
public interface QuinielaRepository extends JpaRepository<QuinielaEntity, Serializable>{
	
	public abstract QuinielaEntity findByIdEstatus(EstatusEntity estatus);

}
