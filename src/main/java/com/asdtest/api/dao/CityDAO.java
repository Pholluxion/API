package com.asdtest.api.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asdtest.api.entitys.City;

public interface CityDAO extends JpaRepository<City, Long> {

	@Query(value = "SELECT * FROM citys u WHERE u.name LIKE %:name%", nativeQuery = true)
	Optional<List<City>> findByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM citys u WHERE u.name LIKE %:name%", nativeQuery = true)
	Optional<City> getByName(@Param("name") String name);


}
