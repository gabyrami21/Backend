package com.example.Book.controller;

import com.example.Book.domain.Editorial;
import com.example.Book.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins="http://localhost:4200")
public class EditorialController{
        @Autowired
        private final EditorialService editorialService;

        public EditorialController(EditorialService editorialService) {
            this.editorialService = editorialService;
        }

        @GetMapping("/public/editorial") //listar las editoriales
        public List<Editorial> list() {
            return editorialService.listAll();
        }

        @GetMapping("/public/editorial/{id}") //obtener una editorial por id
        public ResponseEntity<Editorial> get(@PathVariable Integer id) {
            try {
                Editorial editorial = editorialService.get(id);

                return new ResponseEntity<Editorial>(editorial, HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<Editorial>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/admin/editorial") //crea una editorial
        public void add(@RequestBody Editorial editorial) {
            editorialService.save(editorial);
        }

        @PutMapping("/admin/editorial/{id}")  //actualizar una editorial
        public ResponseEntity<?> update(@RequestBody Editorial editorial, @PathVariable Integer id) {
            try {
                Editorial existingEditorial = editorialService.get(id);
                System.out.println(existingEditorial);
                editorialService.save(editorial);

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/admin/editorial/{id}") //borrar una editorial
        public void delete(@PathVariable Integer id) {
            editorialService.delete(id);
        }
}

