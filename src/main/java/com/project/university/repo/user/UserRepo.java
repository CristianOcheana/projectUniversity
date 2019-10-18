package com.project.university.repo.user;

import com.project.university.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findById(String name);
    List<User> findAll();
}
