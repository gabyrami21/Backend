package com.example.Authentication.service;

import com.example.Authentication.dto.UserAuth;
import com.example.Authentication.repository.AuthenticationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationRepository repo;


    @Autowired
    private PasswordEncoder encoder; // Contraseña encriptada
    public UserAuth login(String username, String pwd) {

        String token = getJWTToken(username);
        UserAuth user = new UserAuth();
        user.setUsername(username);
        user.setPassword(pwd);
        user.setToken(token);
        System.out.println("AJAJAJASLSLSLDHFKJSKSAOEIJSJAJAJAJAJ");
        boolean isPasswordMatch = encoder.matches(pwd,repo.findById(username).get().getPass());

        if(repo.findById(username).isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este usuario no existe");
        System.out.println(repo.findById(username).get().getPass());
        if(repo.findById(username).get().getPass().equals(pwd)) {
            return user;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
        }
    }


    /*
    Entradas:
    Salidas:
    Función: Este metodo captura el JWT para darselas a las credenciales del usuario con una secretkey
     */

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}