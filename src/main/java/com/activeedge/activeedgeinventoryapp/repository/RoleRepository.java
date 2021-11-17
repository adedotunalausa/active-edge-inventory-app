package com.activeedge.activeedgeinventoryapp.repository;

import com.activeedge.activeedgeinventoryapp.enums.RoleType;
import com.activeedge.activeedgeinventoryapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
