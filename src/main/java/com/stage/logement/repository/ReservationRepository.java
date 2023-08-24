package com.stage.logement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.logement.entity.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation , Integer>  {

	



}
