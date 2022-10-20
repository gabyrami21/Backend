package com.example.User.dao;


import com.example.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserRepository extends JpaRepository<User,String> {

    @Query(value = "SELECT * FROM user WHERE nombre like %:nombre%", nativeQuery = true)
    List<User> findUserByName(@Param("nombre") String nombre);

}