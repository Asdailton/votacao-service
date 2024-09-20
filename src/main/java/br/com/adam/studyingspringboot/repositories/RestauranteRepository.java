package br.com.adam.studyingspringboot.repositories;


import br.com.adam.studyingspringboot.model.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteModel, Long> {
   Optional<RestauranteModel> findById(int id);
}
