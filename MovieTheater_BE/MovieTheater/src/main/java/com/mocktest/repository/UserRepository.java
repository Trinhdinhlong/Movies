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
    @Query("FROM User u WHERE u.username = :#{#dto.username}")
    User getByUsername(@Param("dto") UserDto dto);
}
