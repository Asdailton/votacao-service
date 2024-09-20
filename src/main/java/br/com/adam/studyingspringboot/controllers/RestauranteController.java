package br.com.adam.studyingspringboot.controllers;


import br.com.adam.studyingspringboot.dtos.RestauranteDTO;
import br.com.adam.studyingspringboot.infra.exceptions.RestauranteException;
import br.com.adam.studyingspringboot.model.RestauranteModel;
import br.com.adam.studyingspringboot.services.RestauranteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {
    @Autowired
    private RestauranteServices service;
    @GetMapping(value= "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestauranteModel>>findAll() throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody  @Valid RestauranteDTO restauranteDTO)throws Exception{
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.create(restauranteDTO));
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody RestauranteDTO restaurante) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.update(restaurante, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            boolean isDeleted = service.delete(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // 404 Not Found
            }
        } catch (RestauranteException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
