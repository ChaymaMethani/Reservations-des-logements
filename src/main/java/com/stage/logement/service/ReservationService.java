package com.stage.logement.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.logement.exception.NotFoundException;
import com.stage.logement.entity.Reservation;

import com.stage.logement.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired private ReservationRepository repo;
	
	public List<Reservation> findAll() {
		return repo.findAll();
	}
	
	public Reservation save(Reservation reservation) {
		return repo.save(reservation);
	}
	
	public Reservation get(Integer id) throws NotFoundException {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new NotFoundException("Could not find any reservation with id: " + id);
		}
	}
	
	public void delete(Integer id) throws NotFoundException {
		Optional<Reservation> reservation = repo.findById(id);
		
		if (reservation.isEmpty()) {
			throw new NotFoundException("Could not find any reservation with id: " + id);
		}
		
		repo.deleteById(id);
		
	}
	
	public void update(Reservation reserv) throws NotFoundException {
		Reservation reser = repo.findById(reserv.getId()).get();
		if(reser==null) {
			throw new NotFoundException("Could not find any reservation");
		}
		
		}
		
	}

