package com.mocktest.repository;

import com.mocktest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u.username, u.password FROM User u WHERE u.username = :usernames")
//    User getByUsername(String usernames);
}
