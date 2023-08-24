package com.stage.logement.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.stage.logement.entity.Reservation;
import com.stage.logement.exception.NotFoundException;
import com.stage.logement.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

	@Autowired private ReservationService service;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> listAll() {
		List<Reservation> listReservations = service.findAll();
		
		if (listReservations.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return new ResponseEntity<>(listReservations, HttpStatus.OK);
	}
	

	@PostMapping
	public ResponseEntity<Reservation> save(@RequestBody @Valid Reservation reservation) {
		Reservation  savedReser = service.save(reservation);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedReser.getId()).toUri();
		return ResponseEntity.created(uri).body(savedReser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reservation> get(@PathVariable("id") Integer id) throws NotFoundException {
		return new ResponseEntity<>(service.get(id), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Reservation> update(@RequestBody @Valid Reservation reser) {
		return new ResponseEntity<>(service.save(reser), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws NotFoundException {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
}