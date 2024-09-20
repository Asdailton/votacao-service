package br.com.adam.studyingspringboot.services;

import br.com.adam.studyingspringboot.dtos.VoteDTO;
import br.com.adam.studyingspringboot.infra.exceptions.VoteException;
import br.com.adam.studyingspringboot.model.RestauranteModel;
import br.com.adam.studyingspringboot.model.VoteModel;
import br.com.adam.studyingspringboot.repositories.RestauranteRepository;
import br.com.adam.studyingspringboot.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class VoteServices {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;




    public List<VoteModel> findVotesByRestaurantAndDate(Long restauranteId, String registro) {
        List<VoteModel> votes = voteRepository.findByRestauranteIdAndRegistro(restauranteId, registro);
        if (votes.isEmpty()) {
            return new ArrayList<>(); // Retorna uma lista vazia em vez de lançar uma exceção
        }
        return votes;
    }



    public void create(VoteDTO voteDTO) {
        RestauranteModel restaurante = restauranteRepository.findById(voteDTO.id_restaurante())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));

        VoteModel voteEntity = new VoteModel();
        voteEntity.setRegistro(voteDTO.registro());
        voteEntity.setRestaurante(restaurante); // Associa o restaurante à votação

        // Salve a entidade no banco de dados
        VoteModel savedVote = voteRepository.save(voteEntity);

        // Retorne o DTO com os dados da votação
        new VoteDTO(savedVote.getRegistro(), (int) savedVote.getRestaurante().getId());
    }
    }

