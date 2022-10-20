package com.example.Book.service;

import com.example.Book.domain.Editorial;
import com.example.Book.dao.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//Declaración de los métodos que contienen la lógica.
@Service
public class EditorialService {
    @Autowired
    private EditorialRepository repo;

    public List<Editorial> listAll(){
        return repo.findAll();
    }
    public Editorial save(Editorial editorial){
        return repo.save(editorial);
    }
    public Editorial get(Integer id){
        return repo.findById(id).get();
    }
    public void delete(Integer id){
        repo.deleteById(id);
    }


}
