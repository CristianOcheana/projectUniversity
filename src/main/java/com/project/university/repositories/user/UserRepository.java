package com.project.university.repositories.user;

import com.project.university.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();


}
