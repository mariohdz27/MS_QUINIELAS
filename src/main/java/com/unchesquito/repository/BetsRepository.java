package com.unchesquito.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unchesquito.entities.BetsEntity;

@Repository
public interface BetsRepository extends JpaRepository<BetsEntity, Serializable>{

}
