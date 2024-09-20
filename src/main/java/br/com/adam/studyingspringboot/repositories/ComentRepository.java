package br.com.adam.studyingspringboot.repositories;

import br.com.adam.studyingspringboot.model.ComentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentRepository extends JpaRepository<ComentModel, Long> {

}
