package br.com.adam.studyingspringboot.repositories;

import br.com.adam.studyingspringboot.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Long> {
        List<VoteModel> findByRestauranteIdAndRegistro(Long restauranteId, String registro);
    }
