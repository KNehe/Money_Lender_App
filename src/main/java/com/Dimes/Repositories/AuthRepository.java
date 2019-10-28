package com.Dimes.Repositories;

import java.util.Optional;

import com.Dimes.Models.Lender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * AuthRepository
 */
public interface AuthRepository extends JpaRepository<Lender,Integer> {

    
    
   Lender findByUsername(String username);


   //get user id by username
    @Query(value = "SELECT id FROM register WHERE username=?1",nativeQuery = true)
    Optional<Integer> findUserId(String username);

    //get user first name by username
    @Query(value = "SELECT first_name FROM register WHERE username=?1",nativeQuery = true)
    Optional<String> findFirstName(String username);

}