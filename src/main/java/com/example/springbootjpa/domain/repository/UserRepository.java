package com.example.springbootjpa.domain.repository;

import com.example.springbootjpa.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
