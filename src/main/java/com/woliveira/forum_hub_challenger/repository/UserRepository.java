package com.woliveira.forum_hub_challenger.repository;

import com.woliveira.forum_hub_challenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    boolean existsByEmail(String email);

}
