package com.stage.logement.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.stage.logement.entity.Role;
import com.stage.logement.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

	@Autowired private RoleRepository repo;
	
	@Test
	void save() {
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		Role agent = new Role("AGENT");
		
		List<Role> savedRoles = repo.saveAll(List.of(admin, user,agent));
		
		assertNotNull(savedRoles);
		assertEquals(3, savedRoles.size());
	}
}

