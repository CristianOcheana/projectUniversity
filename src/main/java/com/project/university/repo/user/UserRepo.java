package com.project.university.repo.user;

import com.project.university.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByEmail(@Param("email") String email);
}
