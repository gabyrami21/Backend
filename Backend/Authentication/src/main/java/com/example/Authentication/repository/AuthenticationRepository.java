package com.example.Authentication.repository;


import com.example.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Esta clase se extiende del JPARepository para la conexión a la base de datos
* */

@Repository
public interface AuthenticationRepository extends JpaRepository<User, String> {

}

