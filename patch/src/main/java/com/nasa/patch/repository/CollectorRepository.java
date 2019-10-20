package com.nasa.patch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.patch.model.Collector;

public interface CollectorRepository extends JpaRepository<Collector, Integer>{

}
