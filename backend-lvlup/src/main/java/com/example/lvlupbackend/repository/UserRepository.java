package com.example.lvlupbackend.repository;

import com.example.lvlupbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
