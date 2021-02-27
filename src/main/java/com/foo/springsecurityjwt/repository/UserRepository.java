package com.foo.springsecurityjwt.repository;

import com.foo.springsecurityjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Oguz Ozkeroglu
 * Created on 2021.02.27
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
