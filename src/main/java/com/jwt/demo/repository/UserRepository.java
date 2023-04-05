package com.jwt.demo.repository;

import com.jwt.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Transactional
//    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value = "select * from user where username = :username ", nativeQuery = true)
    User findByUsername(String username);

}
