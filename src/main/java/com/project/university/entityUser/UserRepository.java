package com.project.university.entityUser;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findByEmail(String email);

}
