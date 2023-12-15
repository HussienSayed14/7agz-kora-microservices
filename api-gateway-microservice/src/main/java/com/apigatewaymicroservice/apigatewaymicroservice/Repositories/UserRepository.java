package com.apigatewaymicroservice.apigatewaymicroservice.Repositories;


import com.apigatewaymicroservice.apigatewaymicroservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
}
