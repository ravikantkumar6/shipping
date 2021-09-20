package com.sapient.inventory.repository;

import com.sapient.inventory.dto.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipment, Integer> {

}