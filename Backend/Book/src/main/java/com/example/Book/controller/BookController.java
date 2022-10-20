package com.example.Book.controller;


import com.example.Book.domain.Book;
import com.example.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/public/books") //listar los libros
    public List<Book> list() {
        return bookService.listAll();
    }

    @GetMapping("/public/book/{id}") //obtener libro por id
    public ResponseEntity<Book> get(@PathVariable Integer id) {
        try {
            Book book = bookService.get(id);

            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/books") //crear libro
    public void add(@RequestBody Book book) {
        bookService.save(book);
    }

    @PutMapping("/admin/book/{id}")  //actualizar un libro
    public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Integer id) {
        try {
            Book existingBook = bookService.get(id);
            System.out.println(existingBook);
            bookService.save(book);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/book/{id}") //borrar libro
    public void delete(@PathVariable Integer id) {
        bookService.delete(id);
    }
    //Obtiene una lista de libros dado un editorial
    @GetMapping("/books/editorial/{editorialId}")
    public ResponseEntity<List<Book>> listBooksByEditorialId(@PathVariable("editorialId") Integer id){
        List<Book> books = bookService.byEditorialId(id);
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
    //Filtrar libros por nombre.
    @Modifying
    @GetMapping("/book/{name}") //Filter Books by a name.
    public ResponseEntity<List<Book>> filterByName(@PathVariable("name") String name){
        List<Book> books= bookService.filterByName("%" + name + "%");
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
}

