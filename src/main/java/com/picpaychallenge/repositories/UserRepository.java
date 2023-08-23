package com.picpaychallenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpaychallenge.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByDocument(String document);

  Optional<User> findUserById(String id);
}
