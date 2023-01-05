package com.br.niacee.repository;


import com.br.niacee.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {

	
	Optional<UserData> findByEmail(String email);

	boolean existsByEmail(String email);
	
}
