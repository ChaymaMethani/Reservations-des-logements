package com.stage.logement.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;



import com.stage.logement.entity.Reservation;

import com.stage.logement.repository.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTest {

	@Autowired private ReservationRepository repo;
	
	@Test
	void save() {
		Reservation reservation = new Reservation(LocalDate.now(), LocalDate.now().plusDays(10),false,false, null, null);

		Reservation savedReservation = repo.save(reservation);
		
		assertNotNull(savedReservation);
		assertThat(savedReservation.getId()).isGreaterThan(0);
	}

	@Test
	void listAll() {
		List<Reservation> listReservation = repo.findAll();
		
		assertNotNull(listReservation);
		assertThat(listReservation.size()).isGreaterThan(0);
		
		listReservation.forEach(reservation -> System.out.println(reservation));
	}
	
	
}