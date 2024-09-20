package br.com.adam.studyingspringboot.controllers;

import br.com.adam.studyingspringboot.dtos.VoteDTO;
import br.com.adam.studyingspringboot.model.VoteModel;
import br.com.adam.studyingspringboot.services.VoteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VoteController {
    @Autowired
    private VoteServices service;

    @GetMapping("/filter")
    public ResponseEntity<List<VoteModel>> getVotes(@RequestParam Long restauranteId, @RequestParam String registro) {
        List<VoteModel> votes = service.findVotesByRestaurantAndDate(restauranteId, registro);
        if (((List<?>) votes).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(votes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody @Valid VoteDTO vote) throws Exception {
        System.out.println("Registro: " + vote.registro());
        System.out.println("ID do Restaurante: " + vote.id_restaurante());
        service.create(vote);

        return ResponseEntity.status(HttpStatus.CREATED).body("Voto registrado com sucesso");
    }

}
