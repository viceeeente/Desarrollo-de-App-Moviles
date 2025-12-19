package com.example.lvlupbackend.service;

import com.example.lvlupbackend.model.User;
import com.example.lvlupbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User save (User user) {
        return repo.save(user);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
