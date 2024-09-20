package br.com.adam.studyingspringboot.controllers;

import br.com.adam.studyingspringboot.dtos.PersonDTO;
import br.com.adam.studyingspringboot.services.PersonServices;

import br.com.adam.studyingspringboot.model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id ) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>>findAll() throws Exception{
        return  ResponseEntity.status(HttpStatus.OK).body(service.findAll());

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody  @Valid PersonDTO person)throws Exception{
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.create(person));
    }
    @DeleteMapping(value = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception{
        service.delete(id);
         return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");

    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable("id")  Long id, @RequestBody PersonDTO person)throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(person, id));
    }
}
