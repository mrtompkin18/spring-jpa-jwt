package com.healme.app.repository;

import com.healme.app.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
