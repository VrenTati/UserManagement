package org.example.usermanagement.repositories;

import org.example.usermanagement.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}