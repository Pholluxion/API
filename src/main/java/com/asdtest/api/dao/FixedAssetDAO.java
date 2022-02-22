package com.asdtest.api.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asdtest.api.entitys.FixedAsset;

public interface FixedAssetDAO extends JpaRepository<FixedAsset, Long> {
	
	 @Query(value = "SELECT * FROM fixed_assets u WHERE u.serial LIKE %:serial%",nativeQuery = true)
	 Optional<List<FixedAsset>> findBySerial(@Param("serial") String serial);
	 
	 @Query(value = "SELECT * FROM fixed_assets u WHERE u.type LIKE %:type%",nativeQuery = true)
	 Optional<List<FixedAsset>> findByType(@Param("type") String type);
	 
	 @Query(value = "SELECT * FROM fixed_assets u WHERE u.date_of_purchase LIKE %:dateOfPurchase%",nativeQuery = true)
	 Optional<List<FixedAsset>> findByDateOfPurchase(@Param("dateOfPurchase") String dateOfPurchase);

}
