package com.example.middlepoint.user.repository;

import java.util.Optional;

import com.example.middlepoint.user.entity.ERole;
import com.example.middlepoint.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
