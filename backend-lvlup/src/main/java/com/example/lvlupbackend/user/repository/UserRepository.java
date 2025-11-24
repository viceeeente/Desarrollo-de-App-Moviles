package com.example.lvlupbackend.user.repository;

import com.example.lvlupbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
