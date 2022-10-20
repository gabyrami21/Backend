package com.example.User.controller;

/*
    Esta clase contiene las funciones que utiliza el microservicio de usuario
*/

import com.example.User.domain.User;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    * Entradas: url donde se encuentran los usuarios
    * Salidas: Lista de usuarios
    * Funcion: Muestra los usuarios que están registrados
    * */

    @GetMapping("/admin/users")
    public List<User> list() {

        List<User> activos = new ArrayList<>();
        for (User actual : userService.listAll())
        {
            if(actual.isActivo())
            {
                activos.add(actual);
            }
        }
        return activos;
    }


    /*
     * Entradas: url donde se encuentran los usuarios
     * Salidas: Usuario
     * Funcion: Muestra el usuario que está registrados
     * */

    @GetMapping("/admin/user/{username}")
    public ResponseEntity<User> get(@PathVariable("username") final String username) {
        try {
            User user = userService.get(username);
            if(user.isActivo()){
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(NoSuchElementException e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }


    /*
     * Entradas: url donde se encuentran los usuarios
     * Salidas:
     * Funcion: Crea y agrega un nuevo usuario
     * */
    @PostMapping("/admin/users")
    public void add(@RequestBody User user) {
        userService.save(user);
    }


    /*
     * Entradas: url donde se encuentran los usuarios
     * Salidas:
     * Funcion: actualiza la información de un usuario
     * */
    @PutMapping("/admin/user/{username}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable ("username") final String username) {
        try {
            User existingUser = userService.get(username);
            userService.save(user);
            System.out.println(existingUser);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * Entradas: url donde se encuentran los usuarios
     * Salidas:
     * Funcion: Inactiva un usuario
     * */
    @DeleteMapping("/users/{username}")
    public ResponseEntity<User> delete(@PathVariable("username") final String username){
        User existingUser = userService.get(username);
        existingUser.setActivo(false);
        User result = userService.save(existingUser);
        return ResponseEntity.ok(result);
    }

    @Modifying
    @GetMapping("/user/{name}")
    public ResponseEntity<List<User>> filterByName(@PathVariable("name") String name){
        List<User> users= userService.filterByName("%" + name + "%");
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}
