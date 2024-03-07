package com.mocktest.repository;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.Gender;
import com.mocktest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u WHERE u.username = :username")
    User getByUsername(@Param("username") String username);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :#{#dto.password}, u.fullName = :#{#dto.fullName}, " +
            "u.dateOfBirth = :#{#dto.dateOfBirth}, u.gender = :#{#dto.gender}, u.email = :#{#dto.email}, " +
            "u.address = :#{#dto.address}, u.phone = :#{#dto.phone}, u.imageURL = :#{#dto.imageURL} " +
            "WHERE u.username = :#{#dto.username}")
    void updateByUserName(@Param("dto") UserDto dto);
}
