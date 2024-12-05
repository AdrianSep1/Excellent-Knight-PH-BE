package com.ekphl.ekphl.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekphl.ekphl.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByControlNumber(String controlNumber);

    boolean existsByPlateNumber(String plateNumber);
}
